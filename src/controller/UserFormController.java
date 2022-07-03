
package controller;

import com.jfoenix.controls.JFXRadioButton;
import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.User;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;



public class UserFormController implements Initializable{
    public Label userId;
    public TextField txtFullName;
    public TextField txtUEmail;
    public TextField txtGen;

    public TextField txtUserName;
    public TextField txtStatus;
    public TextField txtPassword;
    public TextField txtCPw;
    public DatePicker Dob;
    public TextField rpw;
    public TextField lnm;
    public JFXRadioButton radMale;
    public ToggleGroup Gen;
    public TextField fnm;

    public void btnUserSave(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String gender;
        if (radMale.isSelected()) {
            gender="Male";
        }else{
            gender="Female";
        }

        String dob=Dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        User user = new User(userId.getText(),fnm.getText(),lnm.getText(),txtUEmail.getText(),gender,dob,new String("1"),txtUserName.getText(),txtPassword.getText());


       /*try {
            DbConnection.iud("insert into User values('"+user.getUserId()+"','"+user.getUFullName()+"','"+user.getUFullName()+"','"+user.getUserName()+"'," +
                    "'"+user.getPassword()+"','"+user.getEmail()+"','male','"+user.getDateOfBirth()+"','1')");
           new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...").show();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        if (new  UserSaveController().saveUser(user)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successfully...").show();
            loadUserCount();
            clearField();


        } else {
            new Alert(Alert.AlertType.WARNING, "Saved try again...").show();

        }


    }

    private void clearField() {
        fnm.setText("");
        lnm.setText("");
        radMale.setText("");

        rpw.setText("");
        Dob.setValue(null);
        txtPassword.setText("");
        txtUserName.setText("");
        txtUEmail.setText("");
        radMale.setSelected(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadUserCount();
    }

    private void loadUserCount() {
        int count=0;
        try {
            ResultSet rs=DbConnection.search("select count(UserId) AS x from User");
            if(rs.next()){
                count=Integer.parseInt(rs.getString("x"));
                count++;

            }
            userId.setText("User"+count);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnclear(ActionEvent actionEvent) {
        clearField();
    }

    public void btnUpdate(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String dob=Dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        String gender;
        if (radMale.isSelected()) {
            gender="Male";
        }else{
            gender="Female";
        }

        User user = new User(userId.getText(),fnm.getText(),lnm.getText(),txtUEmail.getText(),gender,dob,new String("1"),txtUserName.getText(),txtPassword.getText());
        if (new  UserSaveController().updateUser(user)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully...").show();
            loadUserCount();




        } else {
            new Alert(Alert.AlertType.WARNING, "Updated try again...").show();

        }


       


    }


}
