import java.util.Map;

public class CashRegister {
    private int registerId;
    private double balance;

    public CashRegister(int registerId, double initialBalance) {
        this.registerId = registerId;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public Receipt sellGoods(Map<Goods, Integer> goodsSold, Cashier cashier, Customer customer) {
        // Check for nulls
        if (cashier == null || customer == null) {
            throw new IllegalArgumentException("Cashier and customer must not be null");
        }

        // Check for no goods sold
        if (goodsSold == null || goodsSold.isEmpty()) {
            throw new IllegalArgumentException("No goods provided to sell");
        }

        double totalCost = calculateTotalCost(goodsSold);

        // check if customer has sufficient balance
        if (totalCost > customer.getBalance()) {
            throw new IllegalArgumentException("Customer doesn't have sufficient balance to make the purchase");
        }

        // existing code for selling goods..

        if (customer.getBalance() >= totalCost) {
            Receipt receipt = new Receipt(cashier, goodsSold, totalCost);
            customer.subtractBalance(totalCost);
            this.balance += totalCost;
            return receipt;
        } else {
            System.out.println("Customer does not have enough money.");
            return null;
        }
    }

    private double calculateTotalCost(Map<Goods, Integer> goodsSold) {
        double totalCost = 0.0;
        for (Map.Entry<Goods, Integer> entry : goodsSold.entrySet()) {
            totalCost += entry.getKey().getPrice() * entry.getValue();
        }
        return totalCost;
    }
}