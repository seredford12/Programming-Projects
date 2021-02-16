package Utilities;

import Model.City;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCity
{
    public static ObservableList<City> getAllCities()
    {
        ObservableList<City> cityList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM city";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int CityID = rs.getInt("cityId");
                String city = rs.getString("city");
                int CountryId = rs.getInt("countryId");
                City cities = new City(CityID, city, CountryId);
                cityList.add(cities);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return cityList;
    }
}
