package Model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Customer
{

    private int customerID;
    private String customerName;
    private int addressID;
    private String address;
    private String address2;
    private int cityID;
    private String postalCode;
    private String phoneNumber;


    public Customer(int customerID, String customerName, int addressID, String address, String address2, int cityID, String postalCode, String phoneNumber)
    {
        this.customerID = customerID;
        this.customerName = customerName;
        this.addressID = addressID;
        this.address = address;
        this.address2 = address2;
        this.cityID = cityID;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    };

    public int getCustomerID() {return this.customerID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}

    public String getCustomerName() {return this.customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}

    public int getAddressID() {return this.addressID;}
    public void setAddressID(int addressID) {this.addressID = addressID;}

    public String getAddress() {return this.address;}
    public void setAddress(String address) {this.address = address;}

    public String getPhoneNumber() {return this.phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAddress2() {return this.address2;}
    public void setAddress2(String address2) {this.address2 = address2;}

    public int getCityID() {return this.cityID;}
    public void setCityID(int cityID) {this.cityID = cityID;}

    public String getPostalCode() {return this.postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}


    @Override
    public String toString()
    {
        return (customerID + " " + customerName);
    }

}
