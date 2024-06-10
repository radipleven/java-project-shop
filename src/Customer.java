import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String name;
    private String id;
    private double balance;
    private Map<Goods, Integer> cart;

    public Customer(String name, String id, double balance){
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.cart = new HashMap<>();
    }

    public void addGoodsToCart(Goods goods, int quantity){
        if(cart.containsKey(goods)){
            cart.put(goods, cart.get(goods) + quantity);
        } else {
            cart.put(goods, quantity);
        }
    }
    public String getName(){
        return this.name;
    }
    public void removeGoodsFromCart(Goods goods, int quantity) {
        if (cart.containsKey(goods)) {
            int currentQuantity = cart.get(goods);
            if (currentQuantity > quantity) {
                cart.put(goods, currentQuantity - quantity);
            }
            else {
                cart.remove(goods);
            }
        }
    }
    public int getQuantityInCart(Goods goods) {
        if (cart.containsKey(goods)) {
            return cart.get(goods);
        } else {
            return 0;
        }
    }
    public String getId(){
        return this.id;
    }
    public boolean isCartEmpty() {
        return cart.isEmpty();
    }
    public double getBalance() {
        return balance;
    }

    public void subtractBalance(double amount) {
        this.balance -= amount;
    }

    public Map<Goods, Integer> getCart() {
        return cart;
    }

    // insert other necessary methods
}