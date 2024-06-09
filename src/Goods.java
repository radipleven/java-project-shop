import java.util.Date;
//package javatest;

public class Goods {
    
    enum  Category
    {
        EDIBLE,
        NONEDIBLE
    }

    private static int globalId=0;
    private int id;
    private String name;
    private double price;
    private Category category;
    private Date expDate;
    private int quantityAvailable;
    //deserialize
    //id!!!
    public Goods(final String newName, double pr, Category cat, final Date exD, int quantityAvailable)
    {
        id=globalId++;
        if (!isValidName(newName))
        {
        }
        if (pr<=0) {
            
        }
        // if (/*date validation */) {
            
        // }
        this.name=newName;
        this.price=pr;
        this.category=cat;
        this.expDate=exD;
        this.quantityAvailable = quantityAvailable;

    }
    public final String getCategory()
    {
        switch (category) {
            case EDIBLE:
                return "Edible";
            case NONEDIBLE:
                return "Nonedible";
            default:
                return "";
        }
    }
    public int getQuantityAvailable() {
        return quantityAvailable;
    }
    public void reduceQuantity(int quantitySold) {
        this.quantityAvailable -= quantitySold;
    }
    public double getPrice()
    {
        return price;
    }
    public final String getName()
    {
        return name;
    }
    private Boolean isValidName(final String name)
    {
        if (name=="" || name==" ") {
            //throw 
        }
        return true;
    }
}

