package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class User {

    public static int id = 1;

    private int userID;
    private String userName;
    private String password;
    private Integer active;

    public User(int userID, String userName, String password, Integer active) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.active = active;
    }

    ;

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return this.active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return ("User Id: " + userID);
    }

}


