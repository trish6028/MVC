package controller;

import db.DbConnection;
import model.Doctor;
import model.PetOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorSaveFormController {



    public List <String> getDoctorId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from doctor ").executeQuery();
        List <String> Id = new ArrayList<>();
        while (rst.next()){
            Id.add(rst.getString(1));
        }return Id;

    }///////////

    public boolean saveDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT into doctor values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, doctor.getDoctorId());
        preparedStatement.setObject(2, doctor.getDoctorName());
        preparedStatement.setObject(3,  doctor.getPosition());
        preparedStatement.setObject(4,  doctor.getDoctorAddress());
        preparedStatement.setObject(5,  doctor.getDoctorSalary());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean updateDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Doctor SET DoctorName=?, Position=?, DoctorAddress=?, DoctorSalary=? WHERE DoctorId=? ");
        preparedStatement.setObject(1, doctor.getDoctorName());
        preparedStatement.setObject(2,  doctor.getPosition());
        preparedStatement.setObject(3,  doctor.getDoctorAddress());
        preparedStatement.setObject(4,  doctor.getDoctorSalary());
        preparedStatement.setObject(5, doctor.getDoctorId());


        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM doctor WHERE DoctorId='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public Doctor getDoctor(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("select * from doctor WHERE DoctorId=?");
        statement.setObject(1,id);
        ResultSet rst = statement.executeQuery();
        if (rst.next()){

            return new Doctor(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4), rst.getDouble(5));


        }else {
            return null;////////////////////////////////////////////////////////////////////////////
        }
    }


    public ArrayList<Doctor> getAllDoctors() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From doctor");
        ArrayList<Doctor> doctors = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            doctors.add(
                    new Doctor(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getDouble(5)
                    ));
        }
        return doctors;
    }



}

