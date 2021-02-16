package View_Controller;

import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {

    private ObservableList<Part>tableItems = Inventory.getAllParts();

    public TextField IDTextField;
    public TextField NameTextField;
    public TextField StockTextField;
    public TextField PriceTextField;
    public TextField MaxTextField;
    public TextField MinTextField;
    @FXML
    private RadioButton InhouseRadioButton;

    @FXML
    private ToggleGroup ToggleGroup2;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private Label ModifyPartLabel2;

    @FXML
    private TextField LabelTextBox;

    private Part part;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void CancelModifyPart(MouseEvent CancelButton) throws IOException
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

        } else {

        }
    }

    @FXML
    void SaveModifyPart(MouseEvent event) throws IOException {

        int max = Integer.parseInt(MaxTextField.getText());
        int min = Integer.parseInt(MinTextField.getText());

        if (max < min) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Error! Max value must be greater than min value.");

            alert.showAndWait();
            return;
        }

        if (InhouseRadioButton.isSelected()) {
        try {
            tableItems.add(new InhousePart(Integer.parseInt(IDTextField.getText()),
                    NameTextField.getText(),
                    Double.parseDouble(PriceTextField.getText()),
                    Integer.parseInt(StockTextField.getText()),
                    Integer.parseInt(MinTextField.getText()),
                    Integer.parseInt(MaxTextField.getText()),
                    Integer.parseInt(LabelTextBox.getText())

            ));
        } catch (NumberFormatException e) {
        }
            tableItems.remove(part);
        }
        else {
        try {
            tableItems.add(new OutsourcedPart(Integer.parseInt(IDTextField.getText()),
                    NameTextField.getText(),
                    Double.parseDouble(PriceTextField.getText()),
                    Integer.parseInt(StockTextField.getText()),
                    Integer.parseInt(MinTextField.getText()),
                    Integer.parseInt(MaxTextField.getText()),
                    LabelTextBox.getText()

            ));
        } catch (NumberFormatException e) {

        }
            tableItems.remove(part);

        }

        Stage stage;
        Parent root;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void RadioButtonChanged() {
        if (ToggleGroup2.getSelectedToggle().equals(InhouseRadioButton))
            ModifyPartLabel2.setText("Machine ID");

        if (ToggleGroup2.getSelectedToggle().equals(OutsourcedRadioButton))
            ModifyPartLabel2.setText("Company Name");
    }

    public void setPart(Part part) {

        this.part = part;

        IDTextField.setText(Integer.toString(part.getPartID()));
        NameTextField.setText(part.getPartName());
        StockTextField.setText(Integer.toString(part.getPartInStock()));
        PriceTextField.setText(Double.toString(part.getPartPrice()));
        MinTextField.setText(Integer.toString(part.getPartMin()));
        MaxTextField.setText(Integer.toString(part.getPartMax()));

        //Inhouse case
        if(part instanceof InhousePart) {
            InhousePart ihpart = (InhousePart) part;
            LabelTextBox.setText(Integer.toString(ihpart.getMachineID()));
            ModifyPartLabel2.setText("Machine ID");
            InhouseRadioButton.setSelected(true);

        }
        //Outsource check
        else {
            OutsourcedPart outpart = (OutsourcedPart) part;
            LabelTextBox.setText(outpart.getCompanyName());
            ModifyPartLabel2.setText("Company Name");
            OutsourcedRadioButton.setSelected(true);
        }
    }
}
