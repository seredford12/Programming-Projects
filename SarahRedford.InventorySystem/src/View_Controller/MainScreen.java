package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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

public class MainScreen implements Initializable {

    public ObservableList<Part> PartInventory = FXCollections.observableArrayList();
    public ObservableList<Product> ProductInventory = FXCollections.observableArrayList();

    @FXML
    private TextField PartsSearchBox;

    @FXML
    private TextField ProductsSearchBox;

    @FXML
    public TableView<Part> PartsTable;

    @FXML
    private TableColumn<Part, Integer> TablePartID;

    @FXML
    private TableColumn<Part,String> TablePartName;

    @FXML
    private TableColumn<Part, Integer> TablePartInStock;

    @FXML
    private TableColumn<Part,Double> TablePartPrice;

    @FXML
    private TableView<Product>ProductsTable;

    @FXML
    private TableColumn<Product, Integer> TableProductID;

    @FXML
    private TableColumn<Product, String> TableProductName;

    @FXML
    private TableColumn<Product,Integer> TableProductInStock;

    @FXML
    private TableColumn<Product,Double> TableProductPrice;

    public Part part;
    public Product product;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PartsTable.setItems(Inventory.getAllParts());
        ProductsTable.setItems(Inventory.getAllProducts());

        TablePartID.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        TablePartName.setCellValueFactory(new PropertyValueFactory<>("PartName"));
        TablePartInStock.setCellValueFactory(new PropertyValueFactory<>("PartInStock"));
        TablePartPrice.setCellValueFactory(new PropertyValueFactory<>("PartPrice"));

        TableProductID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        TableProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        TableProductInStock.setCellValueFactory(new PropertyValueFactory<>("ProductInStock"));
        TableProductPrice.setCellValueFactory(new PropertyValueFactory<>("ProductPrice"));

    }


    @FXML
    void AddPart(MouseEvent AddPartButton) throws IOException {

        System.out.println("On Add Part Button Clicked");
        Parent AddPartParent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene AddPartScene = new Scene(AddPartParent);
        Stage AddPartStage = (Stage) ((Node) AddPartButton.getSource()).getScene().getWindow();
        AddPartStage.setScene(AddPartScene);
        AddPartStage.show();
    }

    @FXML
    void AddProduct(MouseEvent AddProductButton) throws IOException {

        System.out.println("On Add Product Clicked");
        Parent AddProductParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene AddProductScene = new Scene(AddProductParent);
        Stage AddProductStage = (Stage) ((Node) AddProductButton.getSource()).getScene().getWindow();
        AddProductStage.setScene(AddProductScene);
        AddProductStage.show();
    }

    @FXML
    void DeletePart(MouseEvent event) {

        System.out.println("On Delete Part Clicked");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to delete this part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            PartInventory = PartsTable.getItems();
            PartInventory.remove(PartsTable.getSelectionModel().getSelectedItem());

            PartsTable.setItems(PartInventory);

        }
    }

    @FXML
    void DeleteProduct(MouseEvent event) {
        System.out.println("On Delete Product Clicked");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            ProductInventory = ProductsTable.getItems();
            ProductInventory.remove(ProductsTable.getSelectionModel().getSelectedItem());

            ProductsTable.setItems(ProductInventory);

        }
    }

    @FXML
    void ExitSystem(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("On Exit Clicked");
            Platform.exit();
        }

    }

    @FXML
    void ModifyPart(MouseEvent ModifyPartButton) throws IOException {

        System.out.println("On Modify Part Clicked");

        Stage stage;
        Parent root;
        stage = (Stage) ((Node) ModifyPartButton.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModifyPart controller = loader.getController();
        part = PartsTable.getSelectionModel().getSelectedItem();
        controller.setPart(part);



    }

    @FXML
    void ModifyProduct(MouseEvent ModifyProductButton) throws IOException {

        System.out.println("On Modify Product Clicked");

        Stage stage;
        Parent root;
        stage = (Stage) ((Node) ModifyProductButton.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModifyProduct controller = loader.getController();
        product = ProductsTable.getSelectionModel().getSelectedItem();
        controller.setProduct(product);
    }

    @FXML
    void SearchParts(MouseEvent Search) {

        System.out.println("On Search Parts Clicked");

        String parts = PartsSearchBox.getText();

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
        PartsTable.setItems(prts);
        PartsSearchBox.setText("");
    }

    @FXML
    void SearchProducts(MouseEvent event) {

        System.out.println("On Search Products Clicked");

       String Prod = ProductsSearchBox.getText();

       ObservableList<Product> prods = SearchByProductName(Prod);

        try {
            if (prods.size() == 0) {
                int PID = Integer.parseInt(Prod);
                Product p = gettingPID(PID);
                if (p != null) {
                    prods.add(p);}
            }
        } catch (NumberFormatException e) {

        }
        ProductsTable.setItems(prods);
        ProductsSearchBox.setText("");
    }

    private ObservableList<Part> SearchByName(String partialName) {

        ObservableList<Part> PartsNamed = FXCollections.observableArrayList();

        ObservableList<Part> AllParts = Inventory.getAllParts();

        for (Part p : AllParts) {
            if (p.getPartName().contains(partialName)) {
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

    private ObservableList<Product> SearchByProductName(String partialName) {
        ObservableList<Product> ProductsNamed = FXCollections.observableArrayList();
        ObservableList<Product> AllProducts = Inventory.getAllProducts();

        for(Product p : AllProducts) {
            if(p.getProductName().contains(partialName)) {
                ProductsNamed.add(p);
            }
        }



        return ProductsNamed;
    }

    private Product gettingPID(int PID) {

        ObservableList<Product>AllProduct = Inventory.getAllProducts();

        for (int i = 0; i < AllProduct.size(); i++) {
            Product p = AllProduct.get(i);

            if (p.getProductID() == PID)
                return p;
        }

        return null;
    }


}
