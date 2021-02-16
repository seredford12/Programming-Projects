package GUI_Controllers;

import java.time.LocalDateTime;
import Model.Appointment;
import Model.User;
import Utilities.ActionLogFile;
import Utilities.DBAppointment;
import Utilities.DBUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ScheduleCalendar {

    public RadioButton allAppointments;
    public ToggleGroup ToggleGroup1;
    public RadioButton MonthlyAppointments;
    public RadioButton WeeklyAppointments;
    public TableColumn<?, ?> AppointmentIDColumn;
    public TableColumn<?, ?> CustomerIDColumn;
    public TableColumn<?, ?> UserIDColumn;
    public TableColumn<?,?> TitleColumn;
    public TableColumn<?,?> DescriptionColumn;
    public TableColumn<?,?> LocationColumn;
    public TableColumn<?,?> ContactColumn;
    public TableColumn<?,?> TypeColumn;
    public TableColumn<?,?> StartColumn;
    public TableColumn<?,?> EndColumn;
    public TableView<Model.Appointment> AppointmentTable;
    public Appointment appointment;
    public TableColumn<?,?> URLColumn;
    public Label monthAppointmentsLabel;
    public ComboBox<User> UserCombo;
    public Label typeLabel;


    @FXML
    void initialize() {

        if(LogIn.userLogged) {
            ObservableList<Appointment> appointments = DBAppointment.getAllAppointments();
            for(Appointment app: appointments) {
                if(app.getUserID() != User.id) {
                    continue;
                }
                if (app.getStart().isAfter(LocalDateTime.now()) && app.getStart().isBefore(LocalDateTime.now().plusMinutes(15))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setContentText("You have an appointment in less than 15 minutes!");

                    alert.showAndWait();
                    break;
                }
            }
            LogIn.userLogged = false;
        }

        AppointmentTable.setItems(DBAppointment.getAllAppointments());

           AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
           CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
           UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
           TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
           DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
           LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
           ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
           TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
           URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
           StartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
           EndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        monthAppointmentsLabel.setText("Report: There are " +DBAppointment.getAllAppointments().size() + " appointments\n scheduled");
        typeLabel.setText("Click on monthly button\nto get types report.");

        UserCombo.setItems(DBUser.getAllUser());
        UserCombo.setVisibleRowCount(3);

    }

    @FXML
    void AddAppointment(ActionEvent event) throws Exception {

        Parent CustomerListParent = FXMLLoader.load(getClass().getResource("Add_Appointment.fxml"));
        Scene CustomerListScene = new Scene(CustomerListParent);
        Stage CustomerListStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        CustomerListStage.setScene(CustomerListScene);
        CustomerListStage.show();

    }

    @FXML
    void customerList(ActionEvent event) throws Exception {

        Parent CustomerListParent = FXMLLoader.load(getClass().getResource("Customer_List.fxml"));
        Scene CustomerListScene = new Scene(CustomerListParent);
        Stage CustomerListStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        CustomerListStage.setScene(CustomerListScene);
        CustomerListStage.show();

    }

    @FXML
    void deleteAppointment(ActionEvent event)
    {
        DBAppointment.deleteAppointment(AppointmentTable.getSelectionModel().getSelectedItem());

        AppointmentTable.setItems(DBAppointment.getAllAppointments());

        ActionLogFile.userDeleteFile();

    }

    @FXML
    void updateAppointment(ActionEvent event) throws Exception
    {

        Stage stage;
        Parent root;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_Appointment.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        UpdateAppointment controller = loader.getController();
        appointment = AppointmentTable.getSelectionModel().getSelectedItem();
        controller.setAppointment(appointment);

    }


    //Third report shows the total number of appointments for all appointments scheduled, for the total number of monthly appointments scheduled, for the total number of weekly appointments scheduled
    //and for the total number of appointments scheduled for each consultant
    public void ToggleButtonChanged(ActionEvent actionEvent) {

       if(ToggleGroup1.getSelectedToggle().equals(allAppointments))
       {
           AppointmentTable.setItems(DBAppointment.getAllAppointments());

           AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
           CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
           UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
           TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
           DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
           LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
           ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
           TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
           URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
           StartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
           EndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

           monthAppointmentsLabel.setText("Report: There are " +DBAppointment.getAllAppointments().size() + " appointments\n scheduled");
           typeLabel.setText("Click on monthly button\nto get types report.");
       }


       if(ToggleGroup1.getSelectedToggle().equals(MonthlyAppointments))
       {
           AppointmentTable.setItems(DBAppointment.getMonthlyAppointments());

           AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
           CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
           UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
           TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
           DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
           LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
           ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
           TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
           URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
           StartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
           EndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

           monthAppointmentsLabel.setText("Report: There are " +DBAppointment.getMonthlyAppointments().size() + " appointments\n this month");
           typeLabel.setText("There are " + DBAppointment.getAllTypes().size() + " distinct types this\n month" );
       }

           if(ToggleGroup1.getSelectedToggle().equals(WeeklyAppointments))
           {
               AppointmentTable.setItems(DBAppointment.getWeeklyAppointments());

               AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
               CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
               UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
               TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
               DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
               LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
               ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
               TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
               URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
               StartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
               EndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

               monthAppointmentsLabel.setText("Report: There are " +DBAppointment.getWeeklyAppointments().size() + " appointments\n this week.");
               typeLabel.setText("Click on monthly button\nto get types report.");
           }


    }

    public void userCombo(ActionEvent actionEvent)
    {

        if(UserCombo.getValue().getUserID() == 1)
        {
            AppointmentTable.setItems(DBUser.getAllUser1());

            AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
            TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
            TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
            StartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
            EndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

           monthAppointmentsLabel.setText("Report: There are " +DBUser.getAllUser1().size() + " appointments \n" +
                    "scheduled for User 1");
        }

        if(UserCombo.getValue().getUserID() == 2)
        {
            AppointmentTable.setItems(DBUser.getAllUser2());

            AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
            TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
            TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
            StartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
            EndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

            monthAppointmentsLabel.setText("Report: There are " +DBUser.getAllUser2().size() + " appointments\n scheduled for User 2");
        }

        if(UserCombo.getValue().getUserID() == 3)
        {
            AppointmentTable.setItems(DBUser.getAllUser3());

            AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            UserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
            TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
            TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
            StartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
            EndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

            monthAppointmentsLabel.setText("Report: There are " +DBUser.getAllUser3().size() + " appointments\n scheduled for User 3");
        }
    }
}


