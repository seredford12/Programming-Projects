package GUI_Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Model.City;
import Model.Customer;
import Utilities.DBCity;
import Utilities.DBCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddCustomer {

    public Label ChangeCity;
    public ComboBox <City> CityCombo;
    public TextArea AddressTextField;
    public Label ActiveLabel;
    public TextField AddressIDTextField;
    public TextField PostalCodeTextField;
    public TextField CustomerIDTextField;
    public Button SaveButton;
    public Button BackButton;
    public TextArea AddressTextField1;

    @FXML
    private TextField CustomerNameTextField;

    @FXML
    private TextField PhoneNumberTextField;

    @FXML
    void initialize()
    {
        CityCombo.setItems(DBCity.getAllCities());
        CityCombo.setVisibleRowCount(4);
        CityCombo.getSelectionModel().selectFirst();

    }

    @FXML
    void Back(ActionEvent event) throws Exception {

        Parent BackParent = FXMLLoader.load(getClass().getResource("Customer_List.fxml"));
        Scene BackScene = new Scene(BackParent);
        Stage BackStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BackStage.setScene(BackScene);
        BackStage.show();

    }

    @FXML
    void Save(ActionEvent event) throws IOException
    {

        //Check for correct/entered customer information for required fields
        // i.e. customer name entered, address, entered, phone number entered in correct format
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(PhoneNumberTextField.getText());

        if(CustomerNameTextField.getText().isEmpty() | AddressTextField.getText().isEmpty() | PhoneNumberTextField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter all required customer data!");

            alert.showAndWait();
            return;
        }

        if(matcher.matches())
        {
        System.out.println("Valid Phone Number");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter phone number in this format: xxx-xxx-xxxx");

            alert.showAndWait();
            return;
        }


            try {
                DBCustomer.saveCustomer(new Customer(0,
                        CustomerNameTextField.getText(),
                        0,
                        AddressTextField.getText(),
                        AddressTextField1.getText(),
                        CityCombo.getValue().getCityID(),
                        PostalCodeTextField.getText(),
                        PhoneNumberTextField.getText()


                ));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


            Stage stage;
            Parent root;
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer_List.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }
}

