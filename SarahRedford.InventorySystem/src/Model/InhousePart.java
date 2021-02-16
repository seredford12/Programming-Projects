package Model;

public class InhousePart extends Part
{
    private int machineID;

    public InhousePart(int PartID, String Name, double Price, int Stock, int min, int max, int MachineID)
    {
        super(PartID, Name, Price, Stock, min, max);
        setMachineID(MachineID);
    }

    public int getMachineID() {return this.machineID;}
    public void setMachineID(int MachineID) {this.machineID = MachineID;}
}
