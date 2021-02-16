package View_Controller;

import Model.*;
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


public class ModifyProduct implements Initializable {

    private ObservableList<Part>topTable = Inventory.getAllParts();
    private ObservableList<Part>bottomTable = FXCollections.observableArrayList();
    private ObservableList<Product>tableItems = Inventory.getAllProducts();

    public Product product;

    public Product product2;

    @FXML
    private TableColumn<?, ?> IDColumn1;

    @FXML
    private TableColumn<?, ?> NameColumn1;

    @FXML
    private TableColumn<?, ?> StockColumn1;

    @FXML
    private TableColumn<?, ?> PriceColumn1;

    @FXML
    private TableColumn<?, ?> IDColumn2;

    @FXML
    private TableColumn<?, ?> NameColumn2;

    @FXML
    private TableColumn<?, ?> StockColumn2;

    @FXML
    private TableColumn<?, ?> PriceColumn2;


    public TextField IDTextField;
    public TextField NameTextField;
    public TextField InvTextField;
    public TextField PriceTextField;
    public TextField MaxTextField;
    public TextField MinTextField;



    @FXML
    private TableView<Part> ProductTable1;

    @FXML
    private TableView<Part> ProductTable2;

    @FXML
    private TextField ModifyProductSearchBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ProductTable1.setItems(Inventory.getAllParts());


        IDColumn1.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        NameColumn1.setCellValueFactory(new PropertyValueFactory<>("PartName"));
        StockColumn1.setCellValueFactory(new PropertyValueFactory<>("PartInStock"));
        PriceColumn1.setCellValueFactory(new PropertyValueFactory<>("PartPrice"));
        ProductTable1.setItems(topTable);

        IDColumn2.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        NameColumn2.setCellValueFactory(new PropertyValueFactory<>("PartName"));
        StockColumn2.setCellValueFactory(new PropertyValueFactory<>("PartInStock"));
        PriceColumn2.setCellValueFactory(new PropertyValueFactory<>("PartPrice"));
        ProductTable2.setItems(bottomTable);
    }



    @FXML
    void AddModifyProduct(MouseEvent event) {

        Part part = (Part) ProductTable1.getSelectionModel().getSelectedItem();
        if (part == null)
            return;

        bottomTable.add(part);
    }

    @FXML
    void CancelModifyProduct(MouseEvent CancelButton) throws IOException
    {
        System.out.println("Cancel Button was Clicked.");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Are you sure you want to cancel?");

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
    void DeleteModifyProduct(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to delete the selected item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            Part part = (Part) ProductTable2.getSelectionModel().getSelectedItem();
            if (part == null)
                return;

            bottomTable.remove(part);

        } else {

        }
    }

    @FXML
    void SaveModifyProduct(MouseEvent event) throws IOException {

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
            product2 = new Product(Product.id++,
                    NameTextField.getText(),
                    Double.parseDouble(PriceTextField.getText()),
                    Integer.parseInt(InvTextField.getText()),
                    Integer.parseInt(MinTextField.getText()),
                    Integer.parseInt(MaxTextField.getText()));
            tableItems.add(product2);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Inventory.deleteProduct(product);
        product.getAllAssociatedParts().clear();
        product2.getAllAssociatedParts().addAll(bottomTable);


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
    void SearchModifyProduct(MouseEvent event) {

        String parts = ModifyProductSearchBox.getText();

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
        ProductTable1.setItems(prts);
        ModifyProductSearchBox.setText("");

    }

    public void setProduct(Product product) {
        this.product = product;

        IDTextField.setText(Integer.toString(product.getProductID()));
        NameTextField.setText(product.getProductName());
        InvTextField.setText(Integer.toString(product.getProductInStock()));
        PriceTextField.setText(Double.toString(product.getProductPrice()));
        MaxTextField.setText(Integer.toString(product.getProductMax()));
        MinTextField.setText(Integer.toString(product.getProductMin()));

        bottomTable.addAll(product.getAllAssociatedParts());

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

