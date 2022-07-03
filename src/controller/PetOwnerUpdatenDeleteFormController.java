package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.PetOwner;

import java.sql.SQLException;
import java.util.List;

public class PetOwnerUpdatenDeleteFormController {


    public ComboBox cmbOwnerId;
    public TextField txtOwnerName;
    public TextField Contact;
    public TextField Contacttxt;


    public void initialize(){
        try {
            setPetOwnerId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbOwnerId.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) ->{
            try {
                setData(new PetOwnerSaveFormController().getPetOwner((String)newValue));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } ));
    }

    public void btnOnerDelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new PetOwnerSaveFormController().deletePetOwner((String)cmbOwnerId.getValue() ))
        new Alert(Alert.AlertType.CONFIRMATION,"deleted").show();
        else
        new Alert(Alert.AlertType.WARNING,"Try again").show();



    }

    public void btnOwnerUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PetOwner petOwner = new PetOwner((String) cmbOwnerId.getValue(),txtOwnerName.getText(),Contacttxt.getText());
        if (new PetOwnerSaveFormController().updatePetOwner(petOwner))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try again").show();




    }
    public void setData (PetOwner petOwner){
        txtOwnerName.setText(petOwner.getPetOwnerName());
        Contacttxt.setText(petOwner.getContact());

         

    }
    public void setPetOwnerId() throws SQLException, ClassNotFoundException {
        List <String> Id = new PetOwnerSaveFormController().getPetOwnerId();

        cmbOwnerId.getItems().addAll(Id);
    }

}
