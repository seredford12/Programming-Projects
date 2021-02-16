package Model;

public abstract class Part
{

    public static int id = 1;

    private int PartID;
    private String PartName;
    private double Price = 0.0;
    private int Stock;
    private int min;
    private int max;



    public Part(int PartID, String PartName, double PartPrice, int InStock, int min, int max)
    {
        this.PartID = PartID;
        this.PartName = PartName;
        this.Price = PartPrice;
        this.Stock = InStock;
        this.min = min;
        this.max = max;
    };

    public int getPartID() {return this.PartID;}
    public void setPartID(int PartID) {this.PartID = PartID;}

    public String getPartName() {return this.PartName;}
    public void setPartName(String Name) {this.PartName = Name;}

    public double getPartPrice() {return this.Price;}
    public void setPartPrice(double Price) { this.Price = Price;}

    public int getPartInStock() {return this.Stock;}
    public void setPartInStock(int Stock) {this.Stock = Stock;}

    public int getPartMin() {return this.min;}
    public void setPartMin(int min) {this.min = min;}

    public int getPartMax() {return this.max;}
    public void setPartMax(int max) {this.max = max;}

}
