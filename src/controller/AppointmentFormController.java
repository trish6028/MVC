package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Pet;
import model.PetOwner;
import util.Validationutil;
import view.tm.AppointmentTm;
import view.tm.PetTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AppointmentFormController implements Initializable {


    public AnchorPane AppointmentContext;
    public TextField txtApId;


    public JFXTimePicker timeA;
    public JFXDatePicker dateA;
    public ComboBox cmbOid;
    public TextField txtOcontact;
    public TableView tableAppointment;
    public TableColumn colAid;
    public TableColumn colPownerid;
    public TableColumn colcontact;
    public TableColumn colDate;
    public TableColumn coltime;
    public JFXButton btnAsave;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(A0-)[0-9]{2,3}$");
    Pattern contactPattern = Pattern.compile("^[0][0-9]{9}");


    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String date = dateA.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Appointment appointment = new Appointment(txtApId.getText(), (String) cmbOid.getValue(), txtOcontact.getText(), date, timeA.getValue().toString());
        if (new AppointmentSaveFormController().saveAppointment(appointment)) {
            loadAllAppointments(new AppointmentSaveFormController().getAllAppointments());


            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...").show();


        } else {

            new Alert(Alert.AlertType.WARNING, "Saved try again...").show();

        }

    }

    public void btndlt(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new AppointmentSaveFormController().deleteAppointment(txtApId.getText())) {
            loadAllAppointments(new AppointmentSaveFormController().getAllAppointments());


            new Alert(Alert.AlertType.CONFIRMATION, "Deleted...").show();


        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }
    }

    public void btnUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String date = dateA.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Appointment appointment = new Appointment(txtApId.getText(), (String) cmbOid.getValue(), txtOcontact.getText(), date, timeA.getValue().toString());
        if (new AppointmentSaveFormController().updateAppointment(appointment)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...").show();
            loadDataTable();


        } else {
            new Alert(Alert.AlertType.WARNING, "Updated try again...").show();

        }

    }


    public void setOwnerId() throws SQLException, ClassNotFoundException {
        List<String> Id = new PetOwnerSaveFormController().getPetOwnerId();
        cmbOid.getItems().addAll(Id);////////////


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnAsave.setDisable(true);

        storeValidations();


        loadDataTable();


        try {
            setOwnerId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void storeValidations() {
        map.put(txtApId, idPattern);
        map.put(txtOcontact, contactPattern);
    }


    private void loadDataTable() {


        colAid.setCellValueFactory(new PropertyValueFactory<>("AppointmentId"));
        colPownerid.setCellValueFactory(new PropertyValueFactory<>("PetOwnerId"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("Contact"));

        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));

        coltime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            loadAllAppointments(new AppointmentSaveFormController().getAllAppointments());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void loadAllAppointments(ArrayList<Appointment> appointments) {
        ObservableList<AppointmentTm> observableList = FXCollections.observableArrayList();
        appointments.forEach(e -> {
            observableList.add(
                    new AppointmentTm(
                            e.getAppointmentId(),
                            e.getPetOwnerId(),
                            e.getContact(),
                            e.getDate(),
                            e.getTime()
                    ));
        });
        tableAppointment.setItems(observableList);
    }


    public void Apntmnt(KeyEvent keyEvent) {
        btnAsave.setDisable(true);
        Object response = Validationutil.validate(map, btnAsave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Added").showAndWait();

            }

        }
    }
}

