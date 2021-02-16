package GUI_Controllers;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import Model.Appointment;
import Model.Customer;
import Model.User;
import Utilities.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddAppointment {

    public ComboBox<Customer> CIDCombo;
    public ComboBox<String> TypeCombo;
    public ComboBox<LocalTime> StartTimeCombo;
    public ComboBox<LocalTime> EndTimeCombo;
    public TextField URL;
    public javafx.scene.control.DatePicker DatePicker;
    public Appointment app;


    @FXML
    private TextField Contact;

    @FXML
    private TextField Title;

    @FXML
    private TextField Description;

    @FXML
    private TextField Location;

    @FXML
    void initialize() {

        //setting date picker and combo box values
        DatePicker.setValue(LocalDate.now());

        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(17,0);

        LocalTime Estart = LocalTime.of(8, 40);
        LocalTime Eend = LocalTime.of(17, 40);

        while(start.isBefore(end.plusSeconds(1)))
        {
            StartTimeCombo.getItems().add(start);
            start = start.plusMinutes(60);
        }

        while(Estart.isBefore(Eend.plusSeconds(1)))
        {
            EndTimeCombo.getItems().add(Estart);
            Estart = Estart.plusMinutes(60);
        }


        StartTimeCombo.getSelectionModel().selectFirst();
        EndTimeCombo.getSelectionModel().selectFirst();

        CIDCombo.setItems(DBCustomer.getAllCustomers());
        CIDCombo.getSelectionModel().selectFirst();

        TypeCombo.setItems(Appointment.typesList);
        TypeCombo.setVisibleRowCount(2);
        TypeCombo.getSelectionModel().selectFirst();

    }

    @FXML
    void Back(ActionEvent event) throws Exception {

        //return back to Schedule Calendar page without saving or updating
        Parent BackParent = FXMLLoader.load(getClass().getResource("Schedule_Calendar.fxml"));
        Scene BackScene = new Scene(BackParent);
        Stage BackStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BackStage.setScene(BackScene);
        BackStage.show();

    }

    @FXML
    void Save(ActionEvent event) throws Exception {

        //Save an appointment that user would like to add to schedule
        LocalTime begin = LocalTime.of(8, 0);
        LocalTime finish = LocalTime.of(18, 0);
        LocalDate date = DatePicker.getValue();
        LocalTime start = StartTimeCombo.getValue();
        LocalTime end = EndTimeCombo.getValue();
        LocalDateTime startTime = LocalDateTime.of(date, start);
        LocalDateTime endTime = LocalDateTime.of(date, end);

        //End time is before start time
        if (end.isBefore(start)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select an end time later\n than the start time.");

            alert.showAndWait();
            return;
        }

        //Scheduling appointment outside of business hours. Not really necessary because combo boxes do not let you schedule outside of business hours, but wanted to include it just in case.
        if (start.isBefore(begin) | end.isAfter(finish)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please schedule your appointment between 8:00 am\n and 6:00 pm.");

            alert.showAndWait();
            return;
        }

        //Scheduling overlapping appointments
        ObservableList<Appointment> appointments = DBAppointment.getAllAppointments();
            for (Appointment app : appointments) {
                if (app.getUserID() != User.id) {
                    continue;
                }
                if ((app.getStart().isAfter(startTime) | app.getStart().isEqual(startTime)) && app.getStart().isBefore(endTime)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Appointments are overlapping!");

                    alert.showAndWait();
                    return;
                }
                if (app.getEnd().isAfter(startTime) && app.getEnd().isBefore(endTime)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Appointments are overlapping!");

                    alert.showAndWait();
                    return;
                }
                if ((app.getStart().isBefore(startTime) | app.getStart().isEqual(startTime)) && (app.getEnd().isAfter(endTime) | app.getEnd().isEqual(endTime))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("Appointments are overlapping!");

                    alert.showAndWait();
                    return;
                }
            }

            try {
                DBAppointment.saveAppointment(new Appointment(0,
                        CIDCombo.getValue().getCustomerID(),
                        User.id,
                        Title.getText(),
                        Description.getText(),
                        Location.getText(),
                        Contact.getText(),
                        TypeCombo.getValue(),
                        URL.getText(),
                        startTime,
                        endTime
                ));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            ActionLogFile.userSaveFile();

            Stage stage;
            Parent root;
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Schedule_Calendar.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }

}


