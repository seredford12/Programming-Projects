package GUI_Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import Model.Customer;
import Utilities.DBCustomer;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerList {

    ObservableList<Customer> customers = DBCustomer.getAllCustomers();

    public Customer customer;
    public TableColumn<Object, Object> AddressID;
    public TableColumn<?,?> SecondaryAddress;
    public TableColumn<?,?> PostalCode;
    public TableColumn<?,?> CityID;

    @FXML
    private TableView<Customer> CustomerTable;

    @FXML
    private TableColumn<?, ?> CustomerIDColumn;

    @FXML
    private TableColumn<?, ?> NameColumn;

    @FXML
    private TableColumn<?, ?> AddressColumn;

    @FXML
    private TableColumn<?, ?> PhoneNumberColumn;

    @FXML
    private TextField SearchBox;

    @FXML
    void initialize()
    {
        //Set Table Items for Customer List Page
        CustomerTable.setItems(DBCustomer.getAllCustomers());

        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressID.setCellValueFactory(new PropertyValueFactory<>("addressID"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        SecondaryAddress.setCellValueFactory(new PropertyValueFactory<>("address2"));
        CityID.setCellValueFactory(new PropertyValueFactory<>("cityID"));
        PostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


        //Lambda Expression to help user search customer table by name. Helps to make finding customers easy and more efficient.
        FilteredList<Customer> filteredData = new FilteredList<>(customers, p -> true);
        SearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return person.getCustomerName().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(CustomerTable.comparatorProperty());
        CustomerTable.setItems(sortedData);
    }

    @FXML
    void AddCustomer(ActionEvent event) throws Exception {

        Parent addCustomerParent = FXMLLoader.load(getClass().getResource("Add_Customer.fxml"));
        Scene addCustomerScene = new Scene(addCustomerParent);
        Stage addCustomerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addCustomerStage.setScene(addCustomerScene);
        addCustomerStage.show();

    }

    @FXML
    void Back(ActionEvent event) throws Exception {

        Parent BackParent = FXMLLoader.load(getClass().getResource("Schedule_Calendar.fxml"));
        Scene BackScene = new Scene(BackParent);
        Stage BackStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BackStage.setScene(BackScene);
        BackStage.show();

    }

    @FXML
    void DeleteCustomer(ActionEvent event)
    {
        DBCustomer.deleteCustomer(CustomerTable.getSelectionModel().getSelectedItem());

        CustomerTable.setItems(DBCustomer.getAllCustomers());
    }

    @FXML
    void Search(ActionEvent event) {

    }

    @FXML
    void UpdateCustomer(ActionEvent event) throws Exception {

        Stage stage;
        Parent root;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_Customer.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        UpdateCustomer controller = loader.getController();
        customer = CustomerTable.getSelectionModel().getSelectedItem();
        controller.setCustomer(customer);

    }

}

