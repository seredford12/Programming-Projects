package Model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Address
{
    public static int id = 1;

    private int addressID;
    private String address;
    private String address2;
    private int cityID;
    private String postalCode;
    private String phoneNumber;

    public Address(int addressID, String address, String address2, int cityID, String postalCode, String phoneNumber)
    {
        this.addressID = addressID;
        this.address = address;
        this.address2 = address2;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    };

    public int getAddressID() {return this.addressID;}
    public void setAddressID(int addressID) {this.addressID = addressID;}

    public String getAddress() {return this.address;}
    public void setAddress(String address) {this.address = address;}

    public String getAddress2() {return this.address2;}
    public void setAddress2(String address2) {this.address2 = address2;}

    public int getCityID() {return this.cityID;}
    public void setCityID(int cityID) {this.cityID = cityID;}

    public String getPostalCode() {return this.postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    public String getPhoneNumber() {return this.phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

}


