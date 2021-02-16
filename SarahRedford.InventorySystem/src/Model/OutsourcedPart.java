package Model;

public class OutsourcedPart extends Part
{
    private String CompanyName;

    public OutsourcedPart(int PartID, String Name, double Price, int Stock, int min, int max, String CompanyName)
    {
        super(PartID, Name, Price, Stock, min, max);
        setCompanyName(CompanyName);
    }

    public String getCompanyName() {return this.CompanyName;}
    public void setCompanyName(String CompanyName) {this.CompanyName = CompanyName;}


}
