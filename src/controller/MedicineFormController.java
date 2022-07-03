package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Appointment;
import model.Doctor;
import model.Medicine;
import util.Validationutil;
import view.tm.AppointmentTm;
import view.tm.MedicineTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class MedicineFormController implements Initializable {
    public TextField txtMedid;
    public TextField txtName;
    public TextField txtMdec;
    public ComboBox cmbPetId;
    public JFXDatePicker dateM;
    public TableView tableMed;
    public TableColumn colMid;
    public TableColumn colMname;
    public TableColumn colMdes;
    public TableColumn colpId;
    public TableColumn colMdate;
    public JFXComboBox cmbMid;
    public JFXButton btnMsave;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(M0-)[0-9]{2,3}$");
    Pattern namePattern = Pattern.compile( "^[A-z ]{3,20}$");
    Pattern descriptionPattern = Pattern.compile("^[A-z 0-9 ]{3,}$");


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnMsave.setDisable(true);

        storeValidations();


        try {
            setPetId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        colMid.setCellValueFactory(new PropertyValueFactory<>("MedicineId"));
        colMname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colMdes.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colpId.setCellValueFactory(new PropertyValueFactory<>("PetId"));
        colMdate.setCellValueFactory(new PropertyValueFactory<>("Date"));

        try {
            loadAllMedicines(new MedicineSaveFormController().getAllMedicines());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            setDoctorId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbMid.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {

            try {
                setData(new  MedicineSaveFormController().getMedicine((String) newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));/////////


    }

    private void storeValidations() {
        map.put(txtMedid,idPattern);
        map.put(txtName,namePattern);
        map.put(txtMdec,descriptionPattern);
    }


    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String date = dateM.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Medicine medicine = new Medicine(txtMedid.getText(),txtName.getText(),txtMdec.getText(),(String) cmbPetId.getValue(),date);
        if (new MedicineSaveFormController().saveMedicine(medicine)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {

                loadAllMedicines(new MedicineSaveFormController().getAllMedicines());
            }


        } else {
            new Alert(Alert.AlertType.WARNING, "Saved try again...").show();

        }

    }

    public void btnDlt(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new MedicineSaveFormController().deleteMedicine((String)cmbMid.getValue())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {

                loadAllMedicines(new MedicineSaveFormController().getAllMedicines());
            }

        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }


    }




    public void btnupdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String date = dateM.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Medicine medicine = new Medicine( (String)cmbMid.getValue(),txtName.getText(),txtMdec.getText(),(String) cmbPetId.getValue(),date);
        if (new MedicineSaveFormController().updateMedicine(medicine)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Updated", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {

                loadAllMedicines(new MedicineSaveFormController().getAllMedicines());
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Updated try again...").show();

        }


    }





    public void setPetId() throws SQLException, ClassNotFoundException {
        List<String> id = new PetSaveFormController().getPetId();
        cmbPetId.getItems().addAll(id);////////////


    }

    public void setData(Medicine medicine) {

        txtName.setText(medicine.getName());
        txtMdec.setText(medicine.getDescription());
        cmbPetId.setValue(medicine.getPetId());
        dateM.setValue(LocalDate.parse(medicine.getDate()));
    }

    public void setDoctorId() throws SQLException, ClassNotFoundException {

        List<String> Id = new MedicineSaveFormController().getMedicineId();
        cmbMid.getItems().addAll(Id);////////////


    }









    public void loadAllMedicines(ArrayList<Medicine> medicines) {
        ObservableList<MedicineTm> observableList = FXCollections.observableArrayList();
        medicines.forEach(e -> {
            observableList.add(
                    new MedicineTm(
                            e.getMedicineId(),
                            e.getName(),
                            e.getDescription(),
                            e.getPetId(),
                            e.getDate()
                    ));
        });
        tableMed.setItems(observableList);
    }

    public void clearField() {
        txtMedid.clear();
        txtName.clear();
        txtMdec.clear();
        dateM.getEditor().clear();


    }


    public void btnMclear(ActionEvent actionEvent) {
        clearField();


    }


    public void medOnkeyPressed(KeyEvent keyEvent) {
        btnMsave.setDisable(true);
        Object response = Validationutil.validate(map,btnMsave);
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
