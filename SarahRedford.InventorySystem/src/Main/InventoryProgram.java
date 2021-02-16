package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.UUID;


public class InventoryProgram extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args) {



            InhousePart PartA1 = new InhousePart(Part.id++, "Power Supply Unit", 12.99, 15, 3, 110, 111);
            InhousePart PartA2 = new InhousePart(Part.id++, "Hard Drive", 5.25, 85, 70, 210, 222);
            InhousePart PartA3 = new InhousePart(Part.id++, "RAM", 8.46, 11, 5, 105, 333);
            InhousePart PartB6 = new InhousePart(Part.id++, "Optical Drive", 1.36, 9, 1, 180, 211);
            InhousePart PartC9 = new InhousePart(Part.id++, "Computer", 0.85, 55, 50, 125, 311);
            Inventory.addPart(PartA1);
            Inventory.addPart(PartA2);
            Inventory.addPart(PartA3);
            Inventory.addPart(PartB6);
            Inventory.addPart(PartC9);
            OutsourcedPart PartC4 = new OutsourcedPart(Part.id++, "Lego", 5.66, 12, 4, 85, "Great Company Co.");
            OutsourcedPart PartA4 = new OutsourcedPart(Part.id++, "T-Shirt", 9.82, 8, 5, 75, "Max's Toy Co.");
            OutsourcedPart PartB3 = new OutsourcedPart(Part.id++, "TV", 1.28, 100, 95, 225, "The Best Co.");
            Inventory.addPart(PartC4);
            Inventory.addPart(PartA4);
            Inventory.addPart(PartB3);

            Product prod1 = new Product(Product.id++, "Table Cloth", 15.63, 5, 3, 15);
            prod1.addAssociatedPart(PartA1);
            prod1.addAssociatedPart(PartA3);
            Inventory.addProduct(prod1);
            Product prod2 = new Product(Product.id++, "Vacuum", 125.47, 15, 10, 80);
            prod2.addAssociatedPart(PartA2);
            prod2.addAssociatedPart(PartC9);
            Inventory.addProduct(prod2);
            Product prod3 = new Product(Product.id++, "Kitchen Sink", 289.81 , 18, 9, 25);
            prod3.addAssociatedPart(PartA3);
            prod3.addAssociatedPart(PartB3);
            prod3.addAssociatedPart(PartC4);
            Inventory.addProduct(prod3);

        launch(args);

    }


}
