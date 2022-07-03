package controller;

import db.DbConnection;
import model.Appointment;
import model.Doctor;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineSaveFormController {



    public List<String> getMedicineId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from Medicine ").executeQuery();
        List <String> Id = new ArrayList<>();
        while (rst.next()){
            Id.add(rst.getString(1));
        }return Id;


    }


    public boolean saveMedicine(Medicine medicine) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT into Medicine values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,medicine.getMedicineId() );
        preparedStatement.setObject(2, medicine.getName());
        preparedStatement.setObject(3,medicine.getDescription());
        preparedStatement.setObject(4,medicine.getPetId());
        preparedStatement.setObject(5,medicine.getDate());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateMedicine(Medicine medicine) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Medicine SET Name=?, Description=?, PetId=?, Date=? WHERE MedicineId=? ");
        preparedStatement.setObject(1, medicine.getName());
        preparedStatement.setObject(2, medicine.getDescription());
        preparedStatement.setObject(3,  medicine.getPetId());
        preparedStatement.setObject(4, medicine.getDate());
        preparedStatement.setObject(5, medicine.getMedicineId());


        return preparedStatement.executeUpdate() > 0;

    }

    public boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Medicine WHERE MedicineId='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Medicine> getAllMedicines() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From Medicine");
        ArrayList<Medicine> medicines = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            medicines.add(
                    new Medicine(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5)
                    ));
        }
        return medicines;
    }


    public Medicine getMedicine(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("select * from Medicine WHERE MedicineId=?");
        statement.setObject(1,id);
        ResultSet rst = statement.executeQuery();
        if (rst.next()){

            return new Medicine(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getString(5));


        }else {
            return null;////////////////////////////////////////////////////////////////////////////
        }
    }
}
