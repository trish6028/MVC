package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInFormController {
    public JFXTextField txtUserName;
    public Label lblerror;
    public JFXPasswordField txtPassword;
    public AnchorPane LogInFormcontext;


    public void btnLogin(ActionEvent actionEvent) throws IOException {
        LoginFormManager();

    }

    private void LoginFormManager() throws IOException {
        String user = "A";
        String password = "1234";
        if ( txtUserName.getText().equals(user) &&  txtPassword.getText().equals(password)) {
            Stage window = (Stage)  LogInFormcontext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
        } else if ( txtUserName.getText().isEmpty() &&  txtPassword.getText().isEmpty()) {
            lblerror.setText("Your User Name Or Password IS Empty...!");
            txtUserName.clear();
            txtPassword.clear();
        }
        else if (! txtUserName.getText().equals(user)) {
            lblerror.setText("Your User Name is incorrect..!");
            txtUserName.clear();
            txtPassword.clear();
        } else if (! txtPassword.getText().equals(password)) {
            lblerror.setText("Your Password is incorrect..!");
            txtUserName.clear();
            txtPassword.clear();
        }
    }


}
