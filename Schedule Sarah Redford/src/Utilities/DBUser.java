package Utilities;

import Model.Appointment;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBUser
{
    public static ObservableList<User> getAllUser()
    {
        ObservableList<User> userList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM user";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int userID = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                Integer active = rs.getInt("active");
                User user = new User(userID, userName, password, active);
                userList.add(user);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return userList;
    }

    public static ObservableList<Appointment> getAllUser1()
    {
        ObservableList<Appointment> userList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointment WHERE userId = 1";
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
                userList.add(app);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return userList;
    }

    public static ObservableList<Appointment> getAllUser2()
    {
        ObservableList<Appointment> userList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointment WHERE userId = 2";
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
                userList.add(app);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return userList;
    }

    public static ObservableList<Appointment> getAllUser3()
    {
        ObservableList<Appointment> userList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointment WHERE userId = 3";
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
                userList.add(app);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return userList;
    }

}
