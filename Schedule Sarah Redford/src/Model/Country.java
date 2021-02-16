package Model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Country
{
    private int countryID;
    private String country;

    public Country(int countryID, String country)
    {
        this.countryID = countryID;
        this.country = country;
    };

    public int getCountryID() {return this.countryID;}
    public void setCountryID(int countryID) {this.countryID = countryID;}

    public String getCountry() {return this.country;}
    public void setCountry(String country) {this.country = country;}

}
