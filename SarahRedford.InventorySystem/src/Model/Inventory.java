package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.UUID;

public class Inventory
{
    private static ObservableList<Part>allParts = FXCollections.observableArrayList();
    private static ObservableList<Product>allProducts = FXCollections.observableArrayList();




    public static void addPart(Part newPart)
    {
        if (newPart != null)
        {
            allParts.add(newPart);
        }
    }

    public static void addProduct(Product newProduct)
    {
        if (newProduct != null)
        {
            allProducts.add(newProduct);
        }
    }



    public static Part LookUpPart(int PartID)
    {
        if(!allParts.isEmpty())
        {
            for (Part allPart : allParts) {
                if (allPart.getPartID() == PartID)
                    return allPart;
            }
        }
        return null;
    }

    public static Product LookUpProduct(int ProductID)
    {
        if(!allProducts.isEmpty())
        {
            for (Product allProduct : allProducts) {
                if (allProduct.getProductID() == ProductID)
                    return allProduct;
            }
        }
        return null;
    }

    public static ObservableList<Part> LookUpPart(String PartName)
    {
        ObservableList<Part>PartList = FXCollections.observableArrayList();
        if(!allParts.isEmpty())
        {
            for (Part allPart : allParts) {
                if (allPart.getPartName().contains(PartName))
                    PartList.add(allPart);
            }
        }
        return PartList;
    }


    public static ObservableList<Product> LookupProduct(String ProductName)
    {
        ObservableList<Product>ProductList = FXCollections.observableArrayList();
        if(!allProducts.isEmpty())
        {
            for (Product allProduct : allProducts) {
                if (allProduct.getProductName().contains(ProductName))
                    ProductList.add(allProduct);
            }
        }
        return ProductList;
    }



    public static void updatePart(Part selectedPart)
    {
        for(int i = 0; i < allParts.size(); i++)
        {
            if(allParts.get(i).getPartID() == selectedPart.getPartID())
            {
                allParts.set(i,selectedPart);
                break;
            }
        }
    }

    public static void updateProduct(Product selectedProduct)
    {
        for(int i = 0; i < allProducts.size(); i++)
        {
            if(allProducts.get(i).getProductID() == selectedProduct.getProductID())
            {
                allProducts.set(i,selectedProduct);
                break;
            }
        }
    }

    public static boolean deletePart(Part selectedPart)
    {
        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getPartID() == selectedPart.getPartID())
            {
                allParts.remove(i);
                break;
            }
        }
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct)
    {
        for(int i = 0; i < allProducts.size(); i++)
        {
            if(allProducts.get(i).getProductID() == selectedProduct.getProductID())
            {
                allProducts.remove(i);
                break;
            }
        }
        return false;
    }

    public static ObservableList<Part> getAllParts() {return allParts;}

    public static ObservableList<Product> getAllProducts() {return allProducts;}




}
