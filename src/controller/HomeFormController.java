package controller;


import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import model.Appointment;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {
    public Label lblAppointmnt;
    public Label lblPets;
    public Label lblPower;
    public Label lblDoc;
    @FXML
    private LineChart<?, ?> TotalChart;

    @FXML
    private CategoryAxis lblNames;

    @FXML
    private NumberAxis lblqty;
    private Object Appointment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            AppointmentCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PetCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PetOwnerCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            docCount();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        chart();

    }

    private void AppointmentCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Appointment");
        ResultSet rst = stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblAppointmnt.setText(String.valueOf(a));
        }
    }

    private void PetCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Pet");
        ResultSet rst = stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblPets.setText(String.valueOf(a));
        }
    }

    private void PetOwnerCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM PetOwner");
        ResultSet rst = stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblPower.setText(String.valueOf(a));
        }
    }

    private void docCount() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Doctor");
        ResultSet rst = stm.executeQuery();
        int a=0;
        while (rst.next()){
            a++;
            lblDoc.setText(String.valueOf(a));
        }
    }


    public void chart(){
        XYChart.Series set1 = new XYChart.Series<>();



        set1.getData().add(new XYChart.Data("Appointment",Integer.parseInt(lblAppointmnt.getText())));
        set1.getData().add(new XYChart.Data("Pet",Integer.parseInt(lblPets.getText())));
        set1.getData().add(new XYChart.Data("Owner",Integer.parseInt(lblPower.getText())));
        set1.getData().add(new XYChart.Data("Doctor",Integer.parseInt(lblDoc.getText())));

        set1.setName("Total");

        TotalChart.getData().addAll(set1);

    }





}