package controller;


import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.PetOwner;
import util.Validationutil;
import view.tm.PetOwnerTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class PetOwnerFormController{

    public TextField txtOownerId;
    public TextField txtOwnerName;
    public TextField txtContct;
    public JFXButton btnUnD;
    public AnchorPane PetOwnerDetailsContext;
    public TableView petownerTable;
    public TableColumn colloid;
    public TableColumn colName;
    public TableColumn colContact;
    public JFXButton btnSave;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(C0-)[0-9]{2,3}$");
    Pattern namePattern = Pattern.compile( "^[A-z ]{3,20}$");
    Pattern contactPattern = Pattern.compile("^(076|077|075)(||-)[0-9]{7}$|^-$");


    public void initialize(){
        btnSave.setDisable(true);

        storeValidations();


        colloid.setCellValueFactory(new PropertyValueFactory<>("PetOwnerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("PetOwnerName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));


        try {
            loadAllPetOwners(new PetOwnerSaveFormController().getAllPetOwners());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void storeValidations() {
        map.put(txtOownerId,idPattern);
        map.put(txtOwnerName,namePattern);
        map.put(txtContct,contactPattern);

    }

    public void btnOwnerSave(ActionEvent actionEvent)throws SQLException, ClassNotFoundException {
        PetOwner petOwner = new PetOwner(txtOownerId.getText(),txtOwnerName.getText(),txtContct.getText());

        if (new PetOwnerSaveFormController().savePetOwner(petOwner)) {
            loadAllPetOwners(new PetOwnerSaveFormController().getAllPetOwners());
            new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Successfully Try again").show();
        }

    }





    public void btnUnD(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PetOwnerUpdatenDeleteForm.fxml");
        Parent load = FXMLLoader.load(resource);
        PetOwnerDetailsContext.getChildren().clear();
        PetOwnerDetailsContext.getChildren().add(load);


    }

    public void loadAllPetOwners(ArrayList<PetOwner>petOwners) {
        ObservableList<PetOwnerTm> observableList = FXCollections.observableArrayList();
        petOwners.forEach(e->{observableList.add(new PetOwnerTm(e.getPetOwnerId(),e.getPetOwnerName(),e.getContact()));});
        petownerTable.setItems(observableList);
    }

    public void txtPO(KeyEvent keyEvent) {
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

   /* private Object validate() {
        for (TextField textFieldKey : map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()){
                if (!textFieldKey.getText().isEmpty()){
                    textFieldKey.setStyle("-fx-border-color: red");
                }


                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-border-color: blue");

        }
        btnSave.setDisable(false);
        return true;
    }*/
}
    
