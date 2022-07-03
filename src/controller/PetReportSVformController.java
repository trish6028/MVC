package controller;

import db.DbConnection;
import model.Medicine;
import model.PetReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetReportSVformController {
    public boolean savePetReport(PetReport petReport) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT into PetReport values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, petReport.getPetReportId());
        preparedStatement.setObject(2, petReport.getPetId() );
        preparedStatement.setObject(3, petReport.getDoctorId());
        preparedStatement.setObject(4, petReport.getDisease());
        preparedStatement.setObject(5, petReport.getDescription());

        return preparedStatement.executeUpdate() > 0;

    }

    public boolean updatePetReport(PetReport petReport) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement("UPDATE PetReport SET PetId=?,  DoctorId=?, Disease=?, Description=? WHERE PetReportId=? ");
        preparedStatement.setObject(1, petReport.getPetId() );
        preparedStatement.setObject(2, petReport.getDoctorId());
        preparedStatement.setObject(3, petReport.getDisease());
        preparedStatement.setObject(4, petReport.getDescription());
        preparedStatement.setObject(5, petReport.getPetReportId());

        return preparedStatement.executeUpdate() > 0;


    }

    public boolean deletePetReport(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM PetReport WHERE PetReportId='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<PetReport> getAllPetReport() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From PetReport");
        ArrayList<PetReport> petReports = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            petReports.add(
                    new PetReport(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5)
                    ));
        }
        return petReports;
    }
}
