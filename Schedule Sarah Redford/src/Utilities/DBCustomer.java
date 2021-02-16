package Utilities;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCustomer
{
    public static ObservableList<Customer> getAllCustomers()
    {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT customerId, customerName, customer.addressId, address, address2, phone, postalCode, cityId FROM customer, address WHERE customer.addressId = address.addressId";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int customerID = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                int addressID = rs.getInt("addressId");
                String address = rs.getString("address");
                String address2 = rs.getString("address2");
                int cityID = rs.getInt("cityId");
                String postalCode = rs.getString("postalCode");
                String phoneNumber = rs.getString("phone");
                Customer cust = new Customer(customerID, customerName, addressID, address, address2, cityID, postalCode, phoneNumber);
                customerList.add(cust);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }



        return customerList;
    }

    public static void saveCustomer(Customer cust)
    {
        try{

            String sqlA = "INSERT INTO address VALUES (NULL, ?, ?, ?, ?,?, NOW(),'SR', NOW(),'SR')";
            PreparedStatement psA = DBConnection.getConn().prepareStatement(sqlA, Statement.RETURN_GENERATED_KEYS);

            psA.setString(1,cust.getAddress());
            psA.setString(2,cust.getAddress2());
            psA.setInt(3,cust.getCityID());
            psA.setString(4,cust.getPostalCode());
            psA.setString(5,cust.getPhoneNumber());

            psA.execute();

            ResultSet rs = psA.getGeneratedKeys();
            rs.next();
            int addressId = rs.getInt(1);

            String sqlC = "INSERT INTO customer VALUES(NULL, ?, ?, 1, NOW(), 'SR', NOW(), 'SR')";
            PreparedStatement psC = DBConnection.getConn().prepareStatement(sqlC);

            psC.setString(1, cust.getCustomerName());
            psC.setInt(2, addressId);

            psC.execute();


        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void updateCustomer(Customer cust)
    {
        try{

            String sqlA = "UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode = ?, phone = ?, createDate = NOW(), createdBy = 'SR', lastUpdate = NOW(), lastUpdateBy = 'SR' WHERE addressId = ?";
            PreparedStatement psA = DBConnection.getConn().prepareStatement(sqlA, Statement.RETURN_GENERATED_KEYS);


            psA.setString(1,cust.getAddress());
            psA.setString(2,cust.getAddress2());
            psA.setInt(3,cust.getCityID());
            psA.setString(4,cust.getPostalCode());
            psA.setString(5,cust.getPhoneNumber());
            psA.setInt(6, cust.getAddressID());

            psA.execute();

            //ResultSet rs = psA.getGeneratedKeys();
            //rs.next();
            //int addressId = rs.getInt(1);

            String sqlC = "UPDATE customer SET customerName = ?, active = 1, createDate = NOW(), createdBy = 'SR', lastUpdate = NOW(), lastUpdateBy = 'SR' WHERE customerId = ?";
            PreparedStatement psC = DBConnection.getConn().prepareStatement(sqlC);

            psC.setString(1, cust.getCustomerName());
            psC.setInt(2, cust.getCustomerID());
            //psC.setInt(3, addressId);


            //psC.setInt(3, cust.getCustomerID());

            psC.execute();


        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }


    public static void deleteCustomer(Customer cust)
    {
        try{
            String sqlApp = "DELETE FROM appointment WHERE customerId = ?";
            PreparedStatement psApp = DBConnection.getConn().prepareStatement(sqlApp);

            psApp.setInt(1, cust.getCustomerID());

            psApp.execute();

            String sqlC = "DELETE FROM customer WHERE customerId = ?";
            PreparedStatement psC = DBConnection.getConn().prepareStatement(sqlC);

            psC.setInt(1, cust.getCustomerID());

            psC.execute();

            String sqlA = "DELETE FROM address WHERE addressId = ?";
            PreparedStatement psA = DBConnection.getConn().prepareStatement(sqlA);

            psA.setInt(1, cust.getAddressID());

            psA.execute();





        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
