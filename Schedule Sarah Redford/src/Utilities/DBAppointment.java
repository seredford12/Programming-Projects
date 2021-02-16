package Utilities;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DBAppointment
{

    public static ObservableList<Appointment> getAllAppointments()
    {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointment";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int appointmentID = rs.getInt("appointmentId");
                int customerID = rs.getInt("customerId");
                int userID = rs.getInt("userId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contract = rs.getString("contact");
                String type = rs.getString("type");
                String url = rs.getString("url");
                Timestamp start = rs.getTimestamp("start");
                Timestamp end = rs.getTimestamp("end");
                Appointment app = new Appointment(appointmentID, customerID, userID, title, description, location, contract, type, url, start.toLocalDateTime(), end.toLocalDateTime());
                appointmentList.add(app);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return appointmentList;
    }

    //Select All Distinctive Types from Database for one month
    public static ObservableList<Appointment> getAllTypes()
    {
        ObservableList<Appointment> typeList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT DISTINCT type, start FROM appointment WHERE MONTH(start) = MONTH(current_date()) GROUP BY type";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                String type = rs.getString("type");
                Timestamp start = rs.getTimestamp("start");
                Appointment app = new Appointment(0, 0, 0, null, null, null, null, type, null, start.toLocalDateTime(), null);
                typeList.add(app);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return typeList;
    }


    public static ObservableList<Appointment> getMonthlyAppointments()
    {
        //Lambda to easily filter the schedule by month. Much more simple and concise compared to other methods. Much less work than doing it with sql statement.
        ObservableList<Appointment> appointments = getAllAppointments();
        return appointments.filtered(appointment -> {
            LocalDate now = LocalDate.now();
            return appointment.getStart().getMonth() == now.getMonth() && appointment.getStart().getYear() == now.getYear();
        });
    }

    public static ObservableList<Appointment> getWeeklyAppointments()
    {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointment WHERE WEEK(start) = week(current_date())";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int appointmentID = rs.getInt("appointmentId");
                int customerID = rs.getInt("customerId");
                int userID = rs.getInt("userId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contract = rs.getString("contact");
                String type = rs.getString("type");
                String url = rs.getString("url");
                Timestamp start = rs.getTimestamp("start");
                Timestamp end = rs.getTimestamp("end");
                Appointment app = new Appointment(appointmentID, customerID, userID, title, description, location, contract, type, url, start.toLocalDateTime(), end.toLocalDateTime());
                appointmentList.add(app);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return appointmentList;
    }

    public static void saveAppointment(Appointment appointment)
    {
        try{

            String sqlA = "INSERT INTO appointment VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(),'SR',NOW(),'SR')";
            PreparedStatement psA = DBConnection.getConn().prepareStatement(sqlA, Statement.RETURN_GENERATED_KEYS);

            psA.setInt(1,appointment.getCustomerID());
            psA.setInt(2, appointment.getUserID());
            psA.setString(3,appointment.getTitle());
            psA.setString(4,appointment.getDescription());
            psA.setString(5, appointment.getLocation());
            psA.setString(6,appointment.getContact());
            psA.setString(7,appointment.getType());
            psA.setString(8, appointment.getUrl());
            psA.setTimestamp(9, Timestamp.valueOf(appointment.getStart()));
            psA.setTimestamp(10, Timestamp.valueOf(appointment.getEnd()));

            psA.execute();


        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateAppointment(Appointment appointment)
    {
        try{

            String sqlA = "UPDATE appointment SET customerId = ?, userId = ?, title = ?, description = ?, location = ?, contact = ?, type = ?, url = ?, start = ?, end = ?, createDate = NOW(), createdBy = 'SR', lastUpdate = NOW(), lastUpdateBy = 'SR' WHERE appointmentId = ?";
            PreparedStatement psA = DBConnection.getConn().prepareStatement(sqlA, Statement.RETURN_GENERATED_KEYS);


            psA.setInt(1,appointment.getCustomerID());
            psA.setInt(2, appointment.getUserID());
            psA.setString(3, appointment.getTitle());
            psA.setString(4,appointment.getDescription());
            psA.setString(5,appointment.getLocation());
            psA.setString(6, appointment.getContact());
            psA.setString(7, appointment.getType());
            psA.setString(8, appointment.getUrl());
            psA.setTimestamp(9, Timestamp.valueOf(appointment.getStart()));
            psA.setTimestamp(10, Timestamp.valueOf(appointment.getEnd()));
            psA.setInt(11,appointment.getAppointmentID());

            psA.execute();

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void deleteAppointment(Appointment appointment)
    {
        try{

            String sqlC = "DELETE FROM appointment WHERE appointmentId = ?";
            PreparedStatement psA = DBConnection.getConn().prepareStatement(sqlC);

            psA.setInt(1, appointment.getAppointmentID());

            psA.execute();


        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
