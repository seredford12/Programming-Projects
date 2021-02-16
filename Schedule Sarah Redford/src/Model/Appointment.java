package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Appointment
{

    private int appointmentID;
    private int customerID;
    private int userID;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private LocalDateTime start;
    private LocalDateTime end;

    public Appointment(int appointmentID, int customerID, int userID, String title, String description, String location, String contact, String type, String url, LocalDateTime start, LocalDateTime end)
    {
        this.appointmentID = appointmentID;
        this.customerID = customerID;
        this.userID = userID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;

    };

public int getAppointmentID() {return this.appointmentID;}
public void setAppointmentID(int appointmentID) {this.appointmentID = appointmentID;}

public int getCustomerID() {return this.customerID;}
public void setCustomerID(int customerID) {this.customerID = customerID;}

public int getUserID() {return this.userID;}
public void setUserID(int userID) {this.userID = userID;}

public String getTitle() {return this.title;}
public void setTitle(String title) {this.title = title;}

public String getDescription() {return this.description;}
public void setDescription(String description) {this.description = description;}

public String getLocation() {return this.location;}
public void setLocation(String location) {this.location = location;}

public String getContact() {return this.contact;}
public void setContact(String contact) {this.contact = contact;}

public String getType() {return this.type;}
public void setType(String type) {this.type = type;}

public String getUrl() {return this.url;}
public void setUrl(String url) {this.url = url;}

public LocalDateTime getStart() {return this.start;}
public void setStart(LocalDateTime start) {this.start = start;}

public LocalDateTime getEnd() {return this.end;}
public void setEnd(LocalDateTime end) {this.end = end;}

public static ObservableList<String> typesList = FXCollections.observableArrayList("Routine Checkup", "Vaccinations", "Physical Exam");




    @Override
    public String toString()
    {
        return (type);
    }

    }
