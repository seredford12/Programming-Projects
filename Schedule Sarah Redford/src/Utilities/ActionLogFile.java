package Utilities;

import Model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class ActionLogFile {
    private static User user;

    //Creating file to write to
    public static void createFile() {
        try {
            File myFile = new File("Action Log.txt");
            if (myFile.createNewFile()) {
                System.out.println("File Created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //User Login Write to File
    public static void logInFile()
        {

            try {
                FileWriter writer = new FileWriter("Action Log.txt", true);
                writer.append("User id: " + User.id + " has logged in at " + LocalTime.now() + "\n");
                writer.close();
                System.out.println("Wrote to file successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    // User Add New Appointment Write to File
    public static void userSaveFile()
    {
        try {
            FileWriter writer = new FileWriter("Action Log.txt", true);
            writer.append("User ID: " + User.id + " has created an appointment at " + LocalTime.now()+ "\n");
            writer.close();
            System.out.println("Wrote to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // User Update Appointment Write to File
    public static void userUpdateFile()
    {
        try {
            FileWriter writer = new FileWriter("Action Log.txt", true);
            writer.append("User ID: " + User.id + " has updated an appointment at " + LocalTime.now()+ "\n");
            writer.close();
            System.out.println("Wrote to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // User Delete Appointment Write to File
    public static void userDeleteFile()
    {
        try {
            FileWriter writer = new FileWriter("Action Log.txt", true);
            writer.append("User ID: " + User.id + " has deleted an appointment at " + LocalTime.now()+ "\n");
            writer.close();
            System.out.println("Wrote to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


