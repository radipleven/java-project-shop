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
        id = globalId++;
        isValidName(newName);
        if (pr <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.name = newName;
        this.price = pr;
        this.category = cat;
        this.expDate = new Date(exD.getTime());
        this.quantityAvailable = quantityAvailable;
    }
    public final String getCategory()
    {
        switch (category) {
            case EDIBLE:
                return "EDIBLE";
            case NONEDIBLE:
                return "NONEDIBLE";
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
    public Date getExpDate() {
        return new Date(this.expDate.getTime());
    }
    private boolean isValidName(final String name)
    {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return true;
    }
}

