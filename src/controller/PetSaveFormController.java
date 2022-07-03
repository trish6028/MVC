package controller;

import db.DbConnection;
import model.Doctor;
import model.Pet;
import model.PetOwner;
import model.Treatment;
import view.tm.PetTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetSaveFormController {

    public List<String> getPetId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from pet ").executeQuery();
        List <String> Id = new ArrayList<>();
        while (rst.next()){
            Id.add(rst.getString(1));
        }return Id;


    }



    public List<String> getPetOwner() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from pet ").executeQuery();
        List <String> Id = new ArrayList<>();
        while (rst.next()){
            Id.add(rst.getString(1));
        }return Id;

    }///////////



    public boolean savePet(Pet pet)   {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
          // con.setAutoCommit(false);
            String query = "INSERT into pet values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setObject(1, pet.getPetId());
            preparedStatement.setObject(2, pet.getPetAddress());
            preparedStatement.setObject(3, pet.getPetOwnerId());
            preparedStatement.setObject(4, pet.getPetName());
            preparedStatement.setObject(5, pet.getAge());
            preparedStatement.setObject(6, pet.getPetType());
            preparedStatement.setObject(7, pet.getPetTypeId());

            if (preparedStatement.executeUpdate() > 0) {
                if (treatmentDetails(pet.getDoctorId(),pet.getPetId(),pet.getTreatmentName(),pet.getTreatmentType() )){
                   //con.commit();
                    return true;
                }else {
                  // con.rollback();
                    return false;
                }
            }else {
                //con.rollback();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
       }/*finally {
           try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
        return false;

    }

    public boolean treatmentDetails( String DoctorId,String pId,String tName,String tType) throws SQLException, ClassNotFoundException {

            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Treatment VALUES (?,?,?,?)");
            stm.setObject(1,DoctorId);
            stm.setObject(2,pId);
            stm.setObject(3,tName);
            stm.setObject(4,tType);

            return stm.executeUpdate()> 0;


    }

    public ArrayList<PetTm> getAllPets() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From pet");
        ArrayList<PetTm> pets = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            pets.add(
                    new PetTm(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getInt(5),
                            rst.getString(6),
                            rst.getInt(7)



                    ));
        }
        return pets;
    }

    public Doctor getPetOwner(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("select * from petOwner WHERE PetOwnerId=?");
        statement.setObject(1,id);
        ResultSet rst = statement.executeQuery();
        if (rst.next()){

            return new Doctor(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getInt(5));


        }else {
            return null;////////////////////////////////////////////////////////////////////////////
        }
    }


    public boolean updatePet(Pet pet) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement("UPDATE pet SET PetAddress=?, PetOwnerId=?, PetName=?, Age=?, Ptyp=? WHERE PetId=? ");

        preparedStatement.setObject(1,  pet.getPetAddress());
        preparedStatement.setObject(2,  pet.getPetOwnerId());
        preparedStatement.setObject(3,  pet.getPetName());
        preparedStatement.setObject(4,  pet.getAge());

        preparedStatement.setObject(5,  pet.getPetId());


        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deletePet(String id) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM pet WHERE PetId='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }



}

