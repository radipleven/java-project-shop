import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Shop {
    
    private Goods[]storeShelf;
    private int[]countOfGoods;
    private Cashier[] cashiers;
    private Goods[] newlyArrived;
    private Goods[] sold;
    private Receipt[] receipts;
    private int percentEdible;
    private int percentNonedible;

    //constructor from file
    public Shop(String filePath) {
        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Could not read configuration file", e);
        }

        JSONObject shopConfig = new JSONObject(content);

        this.storeShelf = new Goods[shopConfig.getInt("shelfSize")];
        this.countOfGoods = new int[shopConfig.getInt("shelfSize")];
        this.cashiers = new Cashier[shopConfig.getInt("numOfCashiers")];
        this.percentEdible = shopConfig.getInt("percentEdible");
        this.percentNonedible = shopConfig.getInt("percentNonedible");

        JSONArray cashiersJson = shopConfig.getJSONArray("cashiers");
        for (int i = 0; i < cashiersJson.length(); i++) {
            JSONObject cashier = cashiersJson.getJSONObject(i);
            this.cashiers[i] = new Cashier(cashier.getString("name"), cashier.getDouble("salary"));
        }

        JSONArray goodsJson = shopConfig.getJSONArray("goods");
        for (int i = 0; i < goodsJson.length(); i++) {
            JSONObject good = goodsJson.getJSONObject(i);
            this.storeShelf[i] = new Goods( good.getString("name"),
                                            good.getDouble("price"),
                                            Goods.Category.valueOf(good.getString("category").toUpperCase()),
                                            new Date(), 0);
            this.countOfGoods[i] = 0; // default quantity to 0
        }
    }
    //constructor
    public Shop(int shelfSize, int numOfCashiers, int percentEdible, int percentNonedible) {
        this.storeShelf = new Goods[shelfSize];
        this.countOfGoods = new int[shelfSize];
        this.cashiers = new Cashier[numOfCashiers];
        this.newlyArrived = new Goods[10]; // assuming a default size
        this.sold = new Goods[10]; // assuming a default size
        this.receipts = new Receipt[10]; // assuming a default size
        this.percentEdible = percentEdible;
        this.percentNonedible = percentNonedible;
    }

    public Goods getGoodsFromShelf(int index) {
        // make sure to add validation if index can be out of bounds
        return this.storeShelf[index];
    }

    public int getPercentEdible() {
        return this.percentEdible;
    }

    public int getPercentNonedible() {
        return this.percentNonedible;
    }

    public int getNumOfCashiers() {
        return cashiers.length;
    }

    public int getNumOfGoodsOnShelf() {
        return storeShelf.length;
    }

    public void addGoodsToShelf(Goods goods, int quantity) {
        int index = doesGoodsExist(goods.getName());
        if (index != -1) {
            countOfGoods[index] += quantity;
        } else {
            for (int i = 0; i < storeShelf.length; i++) {
                if (storeShelf[i] == null) {
                    storeShelf[i] = goods;
                    countOfGoods[i] = quantity;
                    break;
                }
            }
        }
    }

    public double getGoodsPrice(final String name)
    {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Provided name is null or empty");
        }
        int indx = doesGoodsExist(name);
        if(indx == -1) {
            throw new NoSuchElementException("No Goods found with provided name");
        }
        String type = storeShelf[indx]
                .getCategory()
                .toString(); // ensures the category is expressed as a string

        if(type.equals("EDIBLE")) {
            return storeShelf[indx].getPrice()
                    + (double) percentEdible / 100 * storeShelf[indx].getPrice();
        }
        return storeShelf[indx].getPrice()
                + (double) percentNonedible / 100 * storeShelf[indx].getPrice();
    }

    private int doesGoodsExist(final String name)
    {
        int size=storeShelf.length;
        for(int i=0; i<size; i++)
        {
            if(Objects.equals(storeShelf[i].getName(), name))
            {
                return i;
            }
        }
        return -1;
    }

    public void writeToJson(String filePath) {
        JSONObject shopJson = new JSONObject();
        shopJson.put("shelfSize", this.storeShelf.length);
        shopJson.put("numOfCashiers", this.cashiers.length);
        shopJson.put("percentEdible", this.percentEdible);
        shopJson.put("percentNonedible", this.percentNonedible);

        JSONArray cashiersJson = new JSONArray();
        for (Cashier cashier : this.cashiers) {
            JSONObject cashierJson = new JSONObject();
            cashierJson.put("name", cashier.getName());
            cashierJson.put("salary", cashier.getSalary());
            cashiersJson.put(cashierJson);
        }
        shopJson.put("cashiers", cashiersJson);

        JSONArray goodsJson = new JSONArray();
        for (Goods goods : this.storeShelf) {
            JSONObject goodJson = new JSONObject();
            goodJson.put("name", goods.getName());
            goodJson.put("price", goods.getPrice());
            goodJson.put("category", goods.getCategory().toString());
            goodsJson.put(goodJson);
        }
        shopJson.put("goods", goodsJson);

        try {
            Files.write(Paths.get(filePath), shopJson.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Could not write to JSON file", e);
        }
    }
}
