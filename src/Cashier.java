import java.util.Map;

public class Cashier {
    private String name;
    private static int globalId;
    private int id;
    private double salary;
    private CashRegister assignedRegister; // the register the cashier works at

    public Cashier(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.id = ++globalId; // increment id for each cashier created
    }

    public String getName() {
        return this.name;
    }

    public Receipt sellGoods(Map<Goods, Integer> goodsSold, Customer customer) {
        double totalCost = calculateTotalCost(goodsSold);

        if (customer.getBalance() >= totalCost) {
            Receipt receipt = new Receipt(this, goodsSold, totalCost);
            customer.subtractBalance(totalCost);
            return receipt;
        } else {
            System.out.println("Customer does not have enough money.");
            return null;
        }
    }

    public double calculateTotalCost(Map<Goods, Integer> goodsSold) {
        double totalCost = 0.0;
        for (Map.Entry<Goods, Integer> entry : goodsSold.entrySet()) {
            totalCost += entry.getKey().getPrice() * entry.getValue();
        }
        return totalCost;
    }

    public double getSalary() {
        return salary;
    }

    public void assignRegister(CashRegister register) {
        this.assignedRegister = register;
    }

    public CashRegister getAssignedRegister() {
        return assignedRegister;
    }


}