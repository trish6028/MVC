package controller;

import db.DbConnection;
import model.Appointment;
import model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentSaveFormController {
/*

        public List<String> getPetOwner() throws SQLException, ClassNotFoundException {
                ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from Appointment ").executeQuery();
                List <String> Id = new ArrayList<>();
                while (rst.next()){
                        Id.add(rst.getString(1));
                }return Id;

        }///////////
*/





        public boolean saveAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
                Connection connection = DbConnection.getInstance().getConnection();
                String query = "INSERT into Appointment values(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,appointment.getAppointmentId() );
        preparedStatement.setObject(2, appointment.getPetOwnerId());
        preparedStatement.setObject(3, appointment.getContact());
        preparedStatement.setObject(4, appointment.getDate());
        preparedStatement.setObject(5, appointment.getTime());

        return preparedStatement.executeUpdate() > 0;
    }


        public boolean updateAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
                PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Appointment SET PetOwnerId=?, Contact=?, Date=?, time=? WHERE AppointmentId=? ");

                preparedStatement.setObject(1, appointment.getPetOwnerId());
                preparedStatement.setObject(2, appointment.getContact());
                preparedStatement.setObject(3, appointment.getDate());
                preparedStatement.setObject(4, appointment.getTime());
                preparedStatement.setObject(5, appointment.getAppointmentId());


                return preparedStatement.executeUpdate() > 0;
        }

        public boolean deleteAppointment(String id) throws SQLException, ClassNotFoundException {

                if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Appointment WHERE AppointmentId='"+id+"'").executeUpdate()>0){
                        return true;
                }else{
                        return false;
                }
        }

        public ArrayList<Appointment> getAllAppointments() throws SQLException, ClassNotFoundException {
                PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From Appointment");
                ArrayList<Appointment> appointments = new ArrayList<>();
                ResultSet rst = stm.executeQuery();
                while (rst.next()){
                        appointments.add(
                                new Appointment(
                                        rst.getString(1),
                                        rst.getString(2),
                                        rst.getString(3),
                                        rst.getString(4),
                                        rst.getString(5)
                                ));
                }
                return appointments;
        }


}

