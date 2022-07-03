package controller;

import db.DbConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSaveController {
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnection.getInstance().getConnection();
      String query = "INSERT into User values(?,?,?,?,?,?,?,?,?)";
       PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, user.getUserId());
       preparedStatement.setObject(2, user.getFirstName());
        preparedStatement.setObject(3, user.getLastName());
        preparedStatement.setObject(4, user.getUserName());
        preparedStatement.setObject(5, user.getPassword());
        preparedStatement.setObject(6, user.getEmail());
       preparedStatement.setObject(7, user.getGen());
       preparedStatement.setObject(8, user.getDateOfBirth());
       preparedStatement.setObject(9, user.getStatus());




        return preparedStatement.executeUpdate() > 0;

    }

   public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement("UPDATE user SET FName=?,LName=?  ,Email=?,Gen=?,DOB=?,Status=? ,UserName=?,Password=? WHERE UserId=? ");
       preparedStatement.setObject(1, user.getFirstName());
       preparedStatement.setObject(2, user.getLastName());
       preparedStatement.setObject(3, user.getEmail());
       preparedStatement.setObject(4, user.getGen());
       preparedStatement.setObject(5, user.getDateOfBirth());
       preparedStatement.setObject(6, user.getStatus());
       preparedStatement.setObject(7, user.getUserName());
       preparedStatement.setObject(8, user.getPassword());


       preparedStatement.setObject(9,  "User6");

       return preparedStatement.executeUpdate() > 0;




   }
}
