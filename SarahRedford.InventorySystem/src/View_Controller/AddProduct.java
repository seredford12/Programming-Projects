package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.Part;
import Model.Product;
import com.sun.org.apache.xpath.internal.FoundIndex;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {

    private ObservableList<Part>PartInventory = Inventory.getAllParts();
    private ObservableList<Part>ProductInventory = FXCollections.observableArrayList();

    public Product product;

    public TextField IDTextField;
    public TextField NameTextField;
    public TextField StockTextField;
    public TextField PriceTextField;
    public TextField MaxTextField;
    public TextField MinTextField;

    @FXML
    private TableView<Part> AddProductTable1;

    @FXML
    private TableColumn<?, ?> IDColumn1;

    @FXML
    private TableColumn<?, ?> NameColumn1;

    @FXML
    private TableColumn<?, ?> StockColumn1;

    @FXML
    private TableColumn<?, ?> PriceColumn1;


    @FXML
    private TableView<Part> AddProductTable2;

    @FXML
    private TableColumn<?, ?> IDColumn2;

    @FXML
    private TableColumn<?, ?> NameColumn2;

    @FXML
    private TableColumn<?, ?> StockColumn2;

    @FXML
    private TableColumn<?, ?> PriceColumn2;

    @FXML
    private TextField AddProductSearchBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AddProductTable1.setItems(Inventory.getAllParts());


        IDColumn1.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        NameColumn1.setCellValueFactory(new PropertyValueFactory<>("PartName"));
        StockColumn1.setCellValueFactory(new PropertyValueFactory<>("PartInStock"));
        PriceColumn1.setCellValueFactory(new PropertyValueFactory<>("PartPrice"));
        AddProductTable1.setItems(PartInventory);


        IDColumn2.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        NameColumn2.setCellValueFactory(new PropertyValueFactory<>("PartName"));
        StockColumn2.setCellValueFactory(new PropertyValueFactory<>("PartInStock"));
        PriceColumn2.setCellValueFactory(new PropertyValueFactory<>("PartPrice"));
        AddProductTable2.setItems(ProductInventory);
    }



    @FXML
    void AddAddProduct(MouseEvent event) {

        Part part = (Part) AddProductTable1.getSelectionModel().getSelectedItem();
        if (part == null)
            return;

        ProductInventory.add(part);

    }

    @FXML
    void CancelAddProduct(MouseEvent CancelButton) throws IOException
    {
        System.out.println("Cancel Button was Clicked.");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            Parent CancelButtonParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene CancelButtonScene = new Scene(CancelButtonParent);
            Stage CancelButtonStage = (Stage) ((Node) CancelButton.getSource()).getScene().getWindow();
            CancelButtonStage.setScene(CancelButtonScene);
            CancelButtonStage.show();

        }
    }

    @FXML
    void DeleteAddProduct(MouseEvent event) {

        System.out.println("On Delete Part Clicked");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to delete the selected item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            Part part = (Part) AddProductTable2.getSelectionModel().getSelectedItem();
            if (part == null)
                return;

            ProductInventory.remove(part);
        }
    }

    @FXML
    void SaveAddProduct(MouseEvent event) throws IOException {

        int max = Integer.parseInt(MaxTextField.getText());
        int min = Integer.parseInt(MinTextField.getText());

        if (max < min) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Error! Max value must be greater than min value.");

            alert.showAndWait();
            return;
        }
        try {
            product = new Product(Product.id++,
                    NameTextField.getText(),
                    Double.parseDouble(PriceTextField.getText()),
                    Integer.parseInt(StockTextField.getText()),
                    Integer.parseInt(MinTextField.getText()),
                    Integer.parseInt(MaxTextField.getText()));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        product.getAllAssociatedParts().addAll(ProductInventory);
        Inventory.addProduct(product);



        Stage stage;
        Parent root;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void SearchAddProduct(MouseEvent event) {

        String parts = AddProductSearchBox.getText();

        ObservableList<Part> prts = SearchByName(parts);

        try {
            if (prts.size() == 0) {
                int PartID = Integer.parseInt(parts);
                Part p = gettingID(PartID);
                if (p != null)
                    prts.add(p);
            }

        } catch (NumberFormatException e) {

        }
        AddProductTable1.setItems(prts);
        AddProductSearchBox.setText("");

    }

    private ObservableList<Part> SearchByName(String partialName) {
        ObservableList<Part> PartsNamed = FXCollections.observableArrayList();
        ObservableList<Part> AllParts = Inventory.getAllParts();

        for(Part p : AllParts) {
            if(p.getPartName().contains(partialName)) {
                PartsNamed.add(p);
            }
        }

        return PartsNamed;
    }

    private Part gettingID(int PartID) {
        ObservableList<Part> parts = Inventory.getAllParts();

        for (int i = 0; i < parts.size(); i++) {
            Part p = parts.get(i);

            if (p.getPartID() == PartID) {
                return p;
            }
        }


        return null;
    }

}

