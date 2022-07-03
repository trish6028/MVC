package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Treatment;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TreatmentFormController implements Initializable {

    public ComboBox cmbDid;
    public ComboBox cmbPid;
    public TextField txtTname;
    public TextField txtTtype;

    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Treatment treatment = new Treatment((String) cmbDid.getValue(),(String) cmbPid.getValue(),txtTname.getText(),txtTtype.getText());
        if (new TreatmentSVFormController().saveTreatment(treatment)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Save", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(ButtonType.YES)) {

            }


        } else {
            new Alert(Alert.AlertType.WARNING, "Saved try again...").show();

        }
    }

    public void setDoctorId() throws SQLException, ClassNotFoundException {
        List<String> Id = new DoctorSaveFormController().getDoctorId();
        cmbDid.getItems().addAll(Id);////////////


    }

    public void setPetId() throws SQLException, ClassNotFoundException {
        List<String> Id = new PetSaveFormController().getPetId();
        cmbPid.getItems().addAll(Id);////////////


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            setDoctorId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            setPetId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
