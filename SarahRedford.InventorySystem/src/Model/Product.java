package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product
{

    public static int id = 100;

    private ObservableList<Part>associatedParts = FXCollections.observableArrayList();
    private int ID;
    private String ProductName;
    private double ProductPrice;
    private int ProductInStock;
    private int ProductMin;
    private int ProductMax;

    public Product(int ID, String ProductName, double ProductPrice, int ProductInStock, int ProductMin, int ProductMax)
    {
        this.ID = ID;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductInStock = ProductInStock;
        this.ProductMin = ProductMin;
        this.ProductMax = ProductMax;
    }

    public int getProductID() {return this.ID;}
    public void setProductID(int ProductID) {this.ID = ProductID;}

    public String getProductName() {return this.ProductName;}
    public void setProductName(String Name) {this.ProductName = Name;}

    public double getProductPrice() {return this.ProductPrice;}
    public void setProductPrice(double Price) {this.ProductPrice = Price;}

    public int getProductInStock() {return this.ProductInStock;}
    public void setProductInStock(int Stock) {this.ProductInStock = Stock;}

    public int getProductMin() {return this.ProductMin;}
    public void setProductMin(int min) {this.ProductMin = min;}

    public int getProductMax() {return this.ProductMax;}
    public void setProductMax(int max) {this.ProductMax = max;}

    public void addAssociatedPart(Part part) {associatedParts.add(part);}

    public boolean deleteAssociatedPart(int deletePart)
    {
        for (Part associatedPart : associatedParts) {
            if (associatedPart.getPartID() == deletePart)
                return true;
        }
        return false;
    }

    public ObservableList<Part>getAllAssociatedParts() {return associatedParts;}







}
