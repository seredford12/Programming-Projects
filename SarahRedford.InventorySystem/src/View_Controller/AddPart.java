package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
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
import View_Controller.MainScreen;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPart implements Initializable {



    private ObservableList<Part> tableItems = Inventory.getAllParts();

    @FXML
    private RadioButton InhouseRadioButton;

    @FXML
    private ToggleGroup ToggleGroup1;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private Label AddPartLabel2;

    @FXML
    public TextField IDField;
    public TextField LabelTextBox;
    public TextField NameField;
    public TextField InvField;
    public TextField CostField;
    public TextField MaxField;
    public TextField MinField;

    @FXML
    void CancelAddPart(MouseEvent CancelButton) throws IOException
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
    void SaveAddPart(MouseEvent event) throws IOException {

        int max = Integer.parseInt(MaxField.getText());
        int min = Integer.parseInt(MinField.getText());

        if (max < min) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Error! Max value must be greater than min value.");

            alert.showAndWait();
            return;

        }

        if(InhouseRadioButton.isSelected()) {
            try {
                tableItems.add(new InhousePart(Part.id++,
                        NameField.getText(),
                        Double.parseDouble(CostField.getText()),
                        Integer.parseInt(InvField.getText()),
                        Integer.parseInt(MinField.getText()),
                        Integer.parseInt(MaxField.getText()),
                        Integer.parseInt(LabelTextBox.getText())

                ));
            } catch (NumberFormatException ignored) {
                ignored.printStackTrace();
            }
        }
        else {
            try {
                tableItems.add(new OutsourcedPart(Part.id++,
                        NameField.getText(),
                        Double.parseDouble(CostField.getText()),
                        Integer.parseInt(InvField.getText()),
                        Integer.parseInt(MinField.getText()),
                        Integer.parseInt(MaxField.getText()),
                        LabelTextBox.getText()

                ));
            } catch (NumberFormatException ignored) {
                ignored.printStackTrace();
            }
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

    public void RadioButtonChanged()
    {
        if(ToggleGroup1.getSelectedToggle().equals(InhouseRadioButton))
            AddPartLabel2.setText("Machine ID");

        if(ToggleGroup1.getSelectedToggle().equals(OutsourcedRadioButton))
            AddPartLabel2.setText("Company Name");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

