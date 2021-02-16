package Model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class City
{
    private int cityID;
    private String city;
    private int countryID;

    public City(int cityID, String city, int countryID)
    {
        this.cityID = cityID;
        this.city = city;
        this.countryID = countryID;
    };

    public int getCityID() {return this.cityID;}
    public void setCityID(int cityID) {this.cityID = cityID;}

    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}

    public int getCountryID() {return this.countryID;}
    public void setCountryID(int countryID) {this.countryID = countryID;}

    @Override
    public String toString()
    {
        return (cityID + " " + city);
    }

}
