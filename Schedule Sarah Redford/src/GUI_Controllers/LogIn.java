package GUI_Controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Appointment;
import Model.User;
import Utilities.ActionLogFile;
import Utilities.DBConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LogIn {

    public Label username;
    public Appointment appointment;

    public Label password;
    public Label WelcomeLabel;
    public Button LogInButton;
    public Button exitButton;

    private ResourceBundle resources;

    public static boolean userLogged = true;

    @FXML
    private URL location;

    @FXML
    private TextField usernameTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    void Cancel(ActionEvent event) {

            Platform.exit();

    }

    @FXML
    void LogIn(ActionEvent event) throws Exception {

        //Check for correct username and password
        String username = usernameTextBox.getText();
        String password = passwordTextBox.getText();

        try{
            String sql = "SELECT * FROM user WHERE userName = ? AND password = ?";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(resources.getString("Error"));
                alert.setContentText(resources.getString("noMatch"));

                alert.showAndWait();
            }
            else
            {

                User.id = rs.getInt("userId");
                Stage LogInStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                LogInStage.hide();
                Parent LogInParent = FXMLLoader.load(getClass().getResource("Schedule_Calendar.fxml"));
                Scene LogInScene = new Scene(LogInParent);
                LogInStage.setScene(LogInScene);
                LogInStage.show();

                ActionLogFile.logInFile();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {

        //Translation of Login page to Italian
        resources = ResourceBundle.getBundle("Language/Bundle");
        WelcomeLabel.setText(resources.getString("Welcome"));
        username.setText(resources.getString("username"));
        password.setText(resources.getString("password"));
        LogInButton.setText(resources.getString("logIn"));
        exitButton.setText(resources.getString("Exit"));


    }
}






