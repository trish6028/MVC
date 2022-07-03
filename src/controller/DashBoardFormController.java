package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {
    public AnchorPane DashBoardIncontext;
    public AnchorPane loadFormContext;
    public Label lblDate;
    public Label lbltime;
    public AnchorPane sidepaneContext;






    public void btnPet (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../view/PetForm.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            loadFormContext.getChildren().clear();
            loadFormContext.getChildren().add(load);

        }

        @Override
        public void initialize (URL location, ResourceBundle resources){
            try {
                home();
            } catch (IOException e) {
                e.printStackTrace();
            }
            initClock();

        }

        private void home () throws IOException {
            URL resource = getClass().getResource("../view/HomeForm.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            loadFormContext.getChildren().clear();
            loadFormContext.getChildren().add(load);
        }


        public void btnDashBoard (ActionEvent actionEvent) throws IOException {
            URL resource = getClass().getResource("../view/HomeForm.fxml");
            assert resource != null;
            Parent load = FXMLLoader.load(resource);
            loadFormContext.getChildren().clear();
            loadFormContext.getChildren().add(load);
        }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lbltime.setText(LocalDateTime.now().format(formatter));

            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            lblDate.setText(formatter2.format(date));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    public void btnExit(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage)  loadFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LogInForm.fxml"))));

    }

    public void btnpetowner(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PetOwnerForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnAppointment(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AppointmentForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);

    }

    public void btnPetReport(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PetReportForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);

    }

    public void btnMedicine(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MedicineForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnbill(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/BillForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnDoctor(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DoctorForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);


    }

    public void btnUser(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/UserForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnTreat(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/TreatmentForm.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }
}
