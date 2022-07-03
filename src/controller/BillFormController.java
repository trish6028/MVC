package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Bill;
import model.Doctor;
import model.PetOwner;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.Validationutil;
import view.tm.BillTm;
import view.tm.DoctorTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

import static net.sf.jasperreports.engine.xml.JRXmlLoader.load;

public class BillFormController implements Initializable {
    public TextField txtBid;
    public TextField txtBdesc;

    public JFXComboBox cmbPOid;
    public TextField txtName;
    public TextField txtOcontact;
    public TextField txtPrice;
    public TableView tableBill;
    public TableColumn colBid;
    public TableColumn colBdes;
    public TableColumn colBprice;
    public TableColumn colPOid;
    public TableColumn colPOname;
    public TableColumn colContact;
    public TableColumn colBid1;
    public JFXComboBox cmbBid;
    public Label lblTotal;

    public Button btnPrint;
    public JFXButton btnBsave;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(B0-)[0-9]{2,3}$");
    Pattern descriptionPattern = Pattern.compile("^[A-z 0-9 ]{3,}$");
    Pattern pricePattern = Pattern.compile("^[0-9][0-9][0-9][0-9]*([.])[0-9]{2}?$");



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnBsave.setDisable(true);

        storeValidations();

        try {
            loadAllBills(new BillSaveFormController().getAllBills());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colBid.setCellValueFactory(new PropertyValueFactory<>("BillId"));
        colBdes.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colBprice.setCellValueFactory(new PropertyValueFactory<>("Payment"));
        colPOid.setCellValueFactory(new PropertyValueFactory<>("PetOwnerId"));
        colPOname.setCellValueFactory(new PropertyValueFactory<>("OwnerName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));

        try {
            setBillId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            setPetOwnerId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbPOid.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {

            try {
                setData(new PetOwnerSaveFormController().getPetOwner((String) newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));


        cmbBid.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {

            try {
                setData(new BillSaveFormController().getBill((String) newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));


    }

    private void storeValidations() {

        map.put(txtBid,idPattern);
        map.put(txtBdesc,descriptionPattern);
        map.put(txtPrice,pricePattern);

    }


    public void btnDlt(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new BillSaveFormController().deleteBill((String) cmbBid.getValue())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllBills(new BillSaveFormController().getAllBills());
                clearField();


            }

        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }


    }


    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Bill bill = new Bill(txtBid.getText(), txtBdesc.getText(), Double.parseDouble(txtPrice.getText()), (String) cmbPOid.getValue(), txtName.getText(), txtOcontact.getText());
        if (new BillSaveFormController().saveBill(bill)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllBills(new BillSaveFormController().getAllBills());
                clearField();


            }

        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }

    }

    public void btnUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Bill bill = new Bill((String) cmbBid.getValue(), txtBdesc.getText(), Double.parseDouble(txtPrice.getText()), (String) cmbPOid.getValue(), txtName.getText(), txtOcontact.getText());
        if (new BillSaveFormController().UpdateBill(bill)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllBills(new BillSaveFormController().getAllBills());

            }

        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }


    }


    public void setData(PetOwner petOwner) {


        txtName.setText(petOwner.getPetOwnerName());
        txtOcontact.setText(petOwner.getContact());

    }

    public void setPetOwnerId() throws SQLException, ClassNotFoundException {

        List<String> Id = new PetOwnerSaveFormController().getPetOwnerId();
        cmbPOid.getItems().addAll(Id);////////////


    }

    public void setData(Bill bill) {


        txtBdesc.setText(bill.getDescription());
        txtPrice.setText(String.valueOf(bill.getPayment()));
        cmbPOid.setValue(bill.getPetOwnerId());
        txtName.setText(bill.getOwnerName());
        txtOcontact.setText(bill.getContact());


    }


    public void setBillId() throws SQLException, ClassNotFoundException {

        List<String> Id = new BillSaveFormController().getBillId();
        cmbBid.getItems().addAll(Id);////////////


    }


    public void loadAllBills(ArrayList<Bill> bills) {
        ObservableList<BillTm> observableList = FXCollections.observableArrayList();
        bills.forEach(e -> {
            observableList.add(
                    new BillTm(
                            e.getBillId(),
                            e.getDescription(),
                            e.getPayment(),
                            e.getPetOwnerId(),
                            e.getOwnerName(),
                            e.getContact()
                    ));
        });
        tableBill.setItems(observableList);
    }

    public void clearField() {
        txtBid.clear();
        txtBdesc.clear();
        txtPrice.clear();

        txtName.clear();
        txtOcontact.clear();

    }




    public void PrintClick(MouseEvent mouseEvent) {
        try {
            JasperDesign  design = load(this.getClass().getResourceAsStream("../view/Reports/PaymentBill.jrxml"));

            JasperReport  compileReport = JasperCompileManager.compileReport(design);


            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(compileReport, null, DbConnection.getInstance().getConnection());

                JasperPrintManager.printReport(jasperPrint,false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            JasperViewer.viewReport(jasperPrint,true);

        } catch (JRException e) {
            e.printStackTrace();
        }



    }


    public void billkeyPressed(KeyEvent keyEvent) {
        btnBsave.setDisable(true);
        Object response = Validationutil.validate(map,btnBsave);
        if (keyEvent.getCode()== KeyCode.ENTER){
            if (response instanceof TextField){
                TextField errorText= (TextField) response;
                errorText.requestFocus();
            }else if (response instanceof Boolean){
                new Alert(Alert.AlertType.INFORMATION,"Added").showAndWait();

            }

        }
    }
}
