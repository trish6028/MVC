package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private static Connection connection;

    private static void DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/animalclinic" ,
                "root",
                "1234");
    }
    public static DbConnection getInstance() throws ClassNotFoundException, SQLException {
        if (dbConnection == null) {
            DbConnection();
        }
        return dbConnection;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(dbConnection==null){
            DbConnection();
        }
        return connection;
    }










    public static ResultSet search(String query)throws Exception{
        if (dbConnection == null) {
            DbConnection();
        }
        return connection.createStatement().executeQuery(query);
    }
    public static void iud (String query)throws Exception{
        if (dbConnection == null){
            DbConnection();
        }
        connection.createStatement().executeUpdate(query);

    }

}
