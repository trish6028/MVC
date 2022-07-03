package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Medicine;
import model.PetReport;
import util.Validationutil;
import view.tm.MedicineTm;
import view.tm.PetReportTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class PetReportFormController implements Initializable {
    public TextField txtRid;
    public TextField txtRdes;
    public TextField txtDis;
    public ComboBox cmbDid;
    public ComboBox cmbPetId;
    public TableView tablePetRep;
    public TableColumn colRid;
    public TableColumn colPid;
    public TableColumn colDid;
    public TableColumn coldis;
    public TableColumn colRdes;
    public Button btnPrsave;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(R0-)[0-9]{2,3}$");
    Pattern diseasePattern = Pattern.compile("^[A-z 0-9 ]{3,}$");
    Pattern descriptionPattern = Pattern.compile("^[A-z 0-9 ]{4,}$");


    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        PetReport petReport = new PetReport(txtRid.getText(), (String) cmbPetId.getValue(), (String) cmbDid.getValue(), txtDis.getText(), txtRdes.getText());
        if (new PetReportSVformController().savePetReport(petReport)) {
            loadAllPetReports(new PetReportSVformController().getAllPetReport());


            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...").show();


        } else {
            new Alert(Alert.AlertType.WARNING, "Saved try again...").show();

        }

    }



    public void btnUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        PetReport petReport = new PetReport(txtRid.getText(), (String) cmbPetId.getValue(), (String) cmbDid.getValue(), txtDis.getText(), txtRdes.getText());
        if (new PetReportSVformController().updatePetReport(petReport)) {


            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...").show();


        } else {
            new Alert(Alert.AlertType.WARNING, "Updated try again...").show();

        }


    }

    public void btnDlt(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new PetReportSVformController().deletePetReport(  txtRid.getText())) {



            new Alert(Alert.AlertType.CONFIRMATION, "Deleted...").show();


        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnPrsave.setDisable(true);

        storeValidations();



        try {
            setPetId();
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

        colRid.setCellValueFactory(new PropertyValueFactory<>("PetReportId"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("PetId"));
        colDid.setCellValueFactory(new PropertyValueFactory<>("DoctorId"));
        coldis.setCellValueFactory(new PropertyValueFactory<>("Disease"));
        colRdes.setCellValueFactory(new PropertyValueFactory<>("Description"));

        try {
            loadAllPetReports(new PetReportSVformController().getAllPetReport());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void storeValidations() {

        map.put(txtRid,idPattern);
        map.put( txtDis,diseasePattern );
        map.put(txtRdes,descriptionPattern);
    }

    public void setPetId() throws SQLException, ClassNotFoundException {
        List<String> id = new PetSaveFormController().getPetId();
        cmbPetId.getItems().addAll(id);////////////


    }


    public void setDoctorId() throws SQLException, ClassNotFoundException {
        List<String> id = new  DoctorSaveFormController().getDoctorId();
        cmbDid.getItems().addAll(id);////////////


    }

    public void loadAllPetReports(ArrayList<PetReport> petReports) {
        ObservableList<PetReportTm> observableList = FXCollections.observableArrayList();
        petReports.forEach(e -> {
            observableList.add(
                    new PetReportTm(
                            e.getPetReportId(),
                            e.getPetId(),
                            e.getDoctorId(),
                            e.getDisease(),
                            e.getDescription()
                    ));
        });
        tablePetRep.setItems(observableList);
    }


    public void PrOnkeypressed(KeyEvent keyEvent) {
        btnPrsave.setDisable(true);
        Object response = Validationutil.validate(map,btnPrsave);
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



