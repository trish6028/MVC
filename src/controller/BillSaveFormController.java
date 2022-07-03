package controller;

import db.DbConnection;
import model.Bill;
import model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillSaveFormController {
    public boolean saveBill(Bill bill) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT into Bill values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, bill.getBillId());
        preparedStatement.setObject(2, bill.getDescription());
        preparedStatement.setObject(3, bill.getPayment());
        preparedStatement.setObject(4, bill.getPetOwnerId());
        preparedStatement.setObject(5, bill.getOwnerName());
        preparedStatement.setObject(6, bill.getContact());


        return preparedStatement.executeUpdate() > 0;
    }

    public boolean UpdateBill(Bill bill) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement("UPDATE Bill SET Description=?, Payment=?, PetOwnerId=?, OwnerName=?,Contact=? WHERE BillId=? ");
        preparedStatement.setObject(1, bill.getDescription());
        preparedStatement.setObject(2, bill.getPayment());
        preparedStatement.setObject(3, bill.getPetOwnerId());
        preparedStatement.setObject(4, bill.getContact());
        preparedStatement.setObject(5, bill.getContact());
        preparedStatement.setObject(6, bill.getBillId());

        return preparedStatement.executeUpdate() > 0;
    }

    public boolean deleteBill(String id) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Bill WHERE BillId='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }


    public ArrayList<Bill> getAllBills() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select * From Bill");
        ArrayList<Bill> bills = new ArrayList<>();
        ResultSet rst = stm.executeQuery();
        while (rst.next()){
            bills.add(
                    new Bill(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getDouble(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getString(6)

                    ));
        }
        return bills;
    }


    public List<String> getBillId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("select * from Bill ").executeQuery();
        List <String> Id = new ArrayList<>();
        while (rst.next()){
            Id.add(rst.getString(1));
        }return Id;

    }

    public Bill getBill(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("select * from Bill WHERE BillId=?");
        statement.setObject(1,id);
        ResultSet rst = statement.executeQuery();
        if (rst.next()){

            return new Bill(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getString(4), rst.getString(5), rst.getString(6));


        }else {
            return null;////////////////////////////////////////////////////////////////////////////
        }
    }
}
