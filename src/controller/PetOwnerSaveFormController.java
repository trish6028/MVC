package controller;

import db.DbConnection;
import model.PetOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetOwnerSaveFormController {

    public List<String> getOwnerName() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from PetOwner").executeQuery();
        List<String> Name = new ArrayList<>();
        while (rst.next()) {
            Name.add(rst.getString(2));
        }
        return Name;


    }

    public List<String> getContact() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from PetOwner").executeQuery();
        List<String> Contact = new ArrayList<>();
        while (rst.next()) {
            Contact.add(rst.getString(3));
        }
        return Contact;


    }



    public List<String> getPetOwnerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from PetOwner").executeQuery();
        List<String> Id = new ArrayList<>();
        while (rst.next()) {
            Id.add(rst.getString(1));
        }
        return Id;

    }

    public boolean savePetOwner(PetOwner petOwner) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT into PetOwner values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, petOwner.getPetOwnerId());
        preparedStatement.setObject(2, petOwner.getPetOwnerName());
        preparedStatement.setObject(3, petOwner.getContact());

        return preparedStatement.executeUpdate() > 0;


    }

    public boolean updatePetOwner(PetOwner petOwner) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("UPDATE PetOwner " +
                "SET" +
                " " +
                "OwnerName=?,Contact=? WHERE PetOwnerId=?");
        statement.setObject(1, petOwner.getPetOwnerName());
        statement.setObject(2, petOwner.getContact());
        statement.setObject(3, petOwner.getPetOwnerId());

        return statement.executeUpdate() > 0;

    }

    public boolean deletePetOwner(String Id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("delete from PetOwner WHERE PetOwnerId='" + Id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public PetOwner getPetOwner(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("select * from PetOwner WHERE PetOwnerId=?");
        statement.setObject(1, id);
        ResultSet rst = statement.executeQuery();
        if (rst.next()) {

            return new PetOwner(rst.getString(1), rst.getString(2), rst.getString(3));


        } else {
            return null;
        }
    }

    public ArrayList<PetOwner> getAllPetOwners() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From PetOwner");
        ArrayList<PetOwner> petOwners = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            petOwners.add(new PetOwner(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return petOwners;
    }

}
