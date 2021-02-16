package GUI_Controllers;

import Model.Appointment;
import Model.Customer;
import Model.User;
import Utilities.ActionLogFile;
import Utilities.DBAppointment;
import Utilities.DBCustomer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UpdateAppointment {
    public Button BackButton;
    public TextField AppointmentID;
    public Button SaveButton;
    public TextField UserID;
    public TextField Contact;
    public Label ActiveLabel;
    public Label CityLabel;
    public TextField Title;
    public TextField Description;
    public TextField Location;
    public Appointment appointment;
    public TextField URL;
    public ComboBox<LocalTime> StartTimeCombo;
    public ComboBox<LocalTime> EndTimeCombo;
    public javafx.scene.control.DatePicker DatePicker;
    public ComboBox<Customer> CIDCombo;
    public ComboBox<String> TypeCombo;

    @FXML
    void initialize()
    {
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

    public void Back(ActionEvent actionEvent) throws Exception
    {
        Parent BackParent = FXMLLoader.load(getClass().getResource("Schedule_Calendar.fxml"));
        Scene BackScene = new Scene(BackParent);
        Stage BackStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        BackStage.setScene(BackScene);
        BackStage.show();
    }

    public void Save(ActionEvent actionEvent) throws Exception {


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

        //Scheduling appointment outside of business hours
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
            if ((app.getUserID() != User.id)) {
                continue;
            }
            if(app.getAppointmentID() == Integer.parseInt(AppointmentID.getText())) {
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
            DBAppointment.updateAppointment(new Appointment(this.appointment.getAppointmentID(),
                    CIDCombo.getValue().getCustomerID(),
                    Integer.parseInt(UserID.getText()),
                    Title.getText(),
                    Description.getText(),
                    Location.getText(),
                    Contact.getText(),
                    TypeCombo.getValue(),
                    URL.getText(),
                    startTime,
                    endTime
            ));
        }catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

        ActionLogFile.userUpdateFile();

        Stage stage;
        Parent root;
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Schedule_Calendar.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setAppointment(Appointment appointment)
    {
        this.appointment = appointment;

        AppointmentID.setText(Integer.toString(appointment.getAppointmentID()));
        UserID.setText(Integer.toString(appointment.getUserID()));
        Title.setText(appointment.getTitle());
        Description.setText(appointment.getDescription());
        Location.setText(appointment.getLocation());
        Contact.setText(appointment.getContact());
        URL.setText(appointment.getUrl());
        DatePicker.setValue(appointment.getStart().toLocalDate());
        StartTimeCombo.setValue(appointment.getStart().toLocalTime());
        EndTimeCombo.setValue(appointment.getEnd().toLocalTime());
        TypeCombo.setValue(appointment.getType());

        for(Customer C: CIDCombo.getItems())
        {
            if(C.getCustomerID() == appointment.getCustomerID())
            {
                CIDCombo.setValue(C);
                break;
            }

        }

    }
}
