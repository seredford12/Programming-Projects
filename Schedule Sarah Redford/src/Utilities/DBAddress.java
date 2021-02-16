package Utilities;

import Model.Address;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAddress
{

    public static ObservableList<Address> getAllAddresses()
    {

        ObservableList<Address> addressList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM address";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Integer addressID = rs.getInt("addressId");
                String address = rs.getString("address");
                String address2 = rs.getString("address2");
                Integer cityId = rs.getInt("cityId");
                String postalCode = rs.getString("postalCode");
                String phone = rs.getString("phone");
                Address a = new Address(addressID, address, address2, cityId, postalCode, phone);
                addressList.add(a);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return addressList;
    }

}
