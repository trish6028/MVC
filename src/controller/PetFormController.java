package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Pet;
import model.Treatment;
import view.tm.PetTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PetFormController implements Initializable {
    public AnchorPane PetFormContext;

    public TextField txtPetId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtAge;
    public TextField txtOwnerId;
    public Label lblPetId;
    public ComboBox cmbOId;
    public TableView petTable;

    public TableColumn colpAds;
    public TableColumn colPOid;
    public TableColumn colPname;
    public TableColumn colPage;
    public TableColumn colpetId;
    public TableColumn colPetty;
    public JFXTextField txtPType;
    public JFXComboBox cmbPetType;
    public TableColumn colPtyId;
    public ComboBox<String> cmbDid;
    public TextField txtTname;
    public TextField txtTtype;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            setDoctorId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        loadType();

        loadDataTable();
        try {///////////////
            setOwnerId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }





    public void btnSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {



        int pettpId = 0;
        try {
            ResultSet rs = DbConnection.search("select PetTypeId from PetType where Pettype='" + cmbPetType.getValue().toString() + "'");
            if (rs.next()) {
                pettpId = Integer.parseInt(rs.getString("PetTypeId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Pet pet = new Pet(txtPetId.getText(),
                txtAddress.getText(),
                (String) cmbOId.getValue(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                (String) cmbPetType.getValue(),
                pettpId,
                (String) cmbDid.getValue(),
                txtTname.getText(),
                txtTtype.getText()


        );
        if (new PetSaveFormController().savePet(pet)) {
            loadAllPets(new PetSaveFormController().getAllPets());

            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...").show();

            loadDataTable();


        } else {
            new Alert(Alert.AlertType.WARNING, "Saved try again...").show();

        }
    }



    private void loadDataTable() {

        petTable.getItems().clear();


        colpetId.setCellValueFactory(new PropertyValueFactory<>("PetId"));
        colpAds.setCellValueFactory(new PropertyValueFactory<>("PetAddress"));
        colPOid.setCellValueFactory(new PropertyValueFactory<>("PetOwnerId"));
        colPname.setCellValueFactory(new PropertyValueFactory<>("PetName"));
        colPage.setCellValueFactory(new PropertyValueFactory<>("Age"));
        colPetty.setCellValueFactory(new PropertyValueFactory<>("PetType"));
        colPtyId.setCellValueFactory(new PropertyValueFactory<>("PetTypeId"));


        try {
            loadAllPets(new PetSaveFormController().getAllPets());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        /* loadPetCount();*/


    }

    private void loadType() {
        try {


            ResultSet rs = DbConnection.search("select * from PetType");
            while (rs.next()) {
                cmbPetType.getItems().add(rs.getString("Pettype"));


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }




/*
   private void loadPetCount() {
        int count = 0;
        try {
            ResultSet rs = DbConnection.search("select count(PetId) AS x from Pet");
            if (rs.next()) {
                count = Integer.parseInt(rs.getString("x"));
                count++;

            }
            lblPetId.setText("Pet" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void setOwnerId() throws SQLException, ClassNotFoundException {
        List<String> Id = new PetOwnerSaveFormController().getPetOwnerId();
        cmbOId.getItems().addAll(Id);////////////


    }

    public void loadAllPets(ArrayList<PetTm> pets) {
        ObservableList<PetTm> observableList = FXCollections.observableArrayList();

        pets.forEach(e -> {
            observableList.add(
                    new PetTm(
                            e.getPetId(),
                            e.getPetAddress(),
                            e.getPetOwnerId(),
                            e.getPetName(),
                            e.getAge(),
                            e.getPetType(),
                            e.getPetTypeId()


                    ));
        });

        petTable.setItems(observableList);
    }

    public void btnPupdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int pettpId = 0;
        try {
            ResultSet rs = DbConnection.search("select PetTypeId from PetType where Pettype='" + cmbPetType.getValue().toString() + "'");
            if (rs.next()) {
                pettpId = Integer.parseInt(rs.getString("PetTypeId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
        Pet pet = new Pet(txtPetId.getText(),
                txtAddress.getText(),
                (String) cmbOId.getValue(),
                txtName.getText(),
                Integer.parseInt(txtAge.getText()),
                (String) cmbPetType.getValue(),
                pettpId,);
        if (new PetSaveFormController().updatePet(pet)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully...").show();


        } else {
            new Alert(Alert.AlertType.WARNING, "Updated try again...").show();

        }*/


    }

    public void btnPdelete(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new PetSaveFormController().deletePet(txtPetId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted...").show();


        } else {
            new Alert(Alert.AlertType.WARNING, " try again...").show();


        }

    }

    public void btnAddnewType(ActionEvent actionEvent) {
        if (!txtPType.getText().isEmpty()) {
            try {
                ResultSet rs = DbConnection.search("select * from PetType where Pettype='" + txtPType.getText() + "'");
                if (!rs.next()) {
                    DbConnection.iud("insert into PetType(Pettype) values ('" + txtPType.getText() + "')");
                    txtPType.setText("");
                    System.out.println("Done .........................................");

                } else {

                    System.out.println("This type has been already");
                }

            } catch (Exception e) {
                txtPType.setText("");
                e.printStackTrace();
            }
        } else {
            System.out.println("Must fill the field");
        }


    }

    public void setDoctorId() throws SQLException, ClassNotFoundException {
        List<String> Id = new DoctorSaveFormController().getDoctorId();
        cmbDid.getItems().addAll(Id);////////////


    }


}