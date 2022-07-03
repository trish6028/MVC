package controller;

import db.DbConnection;
import model.Treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TreatmentSVFormController {
    public boolean saveTreatment(Treatment treatment) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT into Treatment values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1,treatment.getDoctorId() );
        preparedStatement.setObject(2,treatment.getPetId());
        preparedStatement.setObject(3,treatment.getTreatmentName());
        preparedStatement.setObject(4,treatment.getTreatmentType());


        return preparedStatement.executeUpdate() > 0;
    }
}
