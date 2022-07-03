package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Doctor;
import model.Pet;
import model.PetOwner;
import util.Validationutil;
import view.tm.DoctorTm;
import view.tm.PetOwnerTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class DoctorFormController {
    public TextField txtDSalary;
    public TextField txtDId;
    public TextField txtDPsn;
    public TextField txtDAddress;
    public TextField txtDName;
    public JFXComboBox cmbDid;
    public TableView tableDoc;
    public TableColumn colDid;
    public TableColumn colName;
    public TableColumn colPsn;
    public TableColumn colDAds;
    public TableColumn colSalary;
    public JFXButton btnSave;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(D0-)[0-9]{2,3}$");
    Pattern namePattern = Pattern.compile( "^[A-z ]{3,20}$");
    Pattern positionPattern = Pattern.compile("^[A-z 0-9 ]{3,8}$");
    Pattern addressPattern = Pattern.compile("^[1-9 A-z\\s . ,]{3,}[A-z]{3,}(.|,)$");
    Pattern salaryPattern = Pattern.compile("^[0-9][0-9][0-9][0-9]*([.])[0-9]{2}?$");


    public void initialize() {
        btnSave.setDisable(true);

        storeValidations();

        colDid.setCellValueFactory(new PropertyValueFactory<>("DoctorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("DoctorName"));
        colPsn.setCellValueFactory(new PropertyValueFactory<>("Position"));
        colDAds.setCellValueFactory(new PropertyValueFactory<>("DoctorAddress"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("DoctorSalary"));


        try {
            loadAllDoctors(new DoctorSaveFormController().getAllDoctors());
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

        cmbDid.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {

            try {
                setData(new DoctorSaveFormController().getDoctor((String) newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));/////////


    }

    private void storeValidations() {
        map.put(txtDId,idPattern);
        map.put(txtDName,namePattern);
        map.put(txtDPsn,positionPattern);
        map.put(txtDAddress,addressPattern );
        map.put(txtDSalary,salaryPattern);

    }

    public void btnDsave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor(txtDId.getText(), txtDName.getText(), txtDPsn.getText(), txtDAddress.getText(), Double.parseDouble(txtDSalary.getText()));
        if (new DoctorSaveFormController().saveDoctor(doctor)) {


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllDoctors(new DoctorSaveFormController().getAllDoctors());
                clearField();


            }

        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }
    }

    public void clearField() {
        txtDId.clear();
        txtDName.clear();
        txtDPsn.clear();
        txtDAddress.clear();
        txtDSalary.clear();

    }

    public void btnDUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Doctor doctor = new Doctor((String) cmbDid.getValue(), txtDName.getText(), txtDPsn.getText(), txtDAddress.getText(), Double.parseDouble(txtDSalary.getText()));
        if (new DoctorSaveFormController().updateDoctor(doctor)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Update", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllDoctors(new DoctorSaveFormController().getAllDoctors());

            }

        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();

        }
    }

    public void btnclear(ActionEvent actionEvent) {
        clearField();
    }

    public void btndelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DoctorSaveFormController().deleteDoctor((String) cmbDid.getValue())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Delete", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {
                loadAllDoctors(new DoctorSaveFormController().getAllDoctors());
                clearField();

            }

        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }
    }

    public void setData(Doctor doctor) {


        txtDName.setText(doctor.getDoctorName());
        txtDPsn.setText(doctor.getPosition());
        txtDAddress.setText(doctor.getDoctorAddress());
        txtDSalary.setText(String.valueOf(doctor.getDoctorSalary()));
    }

    public void setDoctorId() throws SQLException, ClassNotFoundException {

        List<String> Id = new DoctorSaveFormController().getDoctorId();
        cmbDid.getItems().addAll(Id);////////////


    }

    public void loadAllDoctors(ArrayList<Doctor> doctors) {
        ObservableList<DoctorTm> observableList = FXCollections.observableArrayList();
        doctors.forEach(e -> {
            observableList.add(
                    new DoctorTm(
                            e.getDoctorId(),
                            e.getDoctorName(),
                            e.getPosition(),
                            e.getDoctorAddress(),

                            e.getDoctorSalary()
                    ));
        });
        tableDoc.setItems(observableList);
    }


    public void DoctorOnkey(KeyEvent keyEvent) {
        btnSave.setDisable(true);
        Object response = Validationutil.validate(map,btnSave);
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



