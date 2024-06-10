import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Receipt {
    private static int globalId = 0;
    private static int totalReceipts = 0;
    private static double totalTurnover = 0;

    private int id;
    private Cashier worker;
    private LocalDateTime dateTimeOfCreation;
    private Map<Goods, Integer> goodsSold;
    private double total;

    public Receipt(Cashier worker, Map<Goods, Integer> goodsSold, double total) {
        this.id = ++globalId;
        this.worker = worker;
        this.dateTimeOfCreation = LocalDateTime.now();
        this.goodsSold = goodsSold;
        this.total = total;

        Receipt.totalReceipts++; // Increment total number of receipts
        Receipt.totalTurnover += this.total; // Add total to turnover

        this.saveToFile(); // Save receipt content to a file
    }

    private void saveToFile() {
        try {
            FileWriter writer = new FileWriter("./Receipt" + this.id + ".txt");
            writer.write(this.toString()); // Write receipt content
            writer.close();
        } catch(IOException e) {
            System.err.println("Error occurred while saving the receipt.");
        }
    }

    public static int getTotalReceipts() {
        return totalReceipts;
    }

    public static double getTotalTurnover() {
        return totalTurnover;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Receipt ID: ").append(this.id).append("\n")
                .append("Cashier: ").append(this.worker.getName()).append("\n") // assuming worker class has a getName() method.
                .append("Date and Time: ").append(this.dateTimeOfCreation.toString()).append("\n")
                .append("Items: \n");

        for (Map.Entry<Goods, Integer> entry : this.goodsSold.entrySet()) {
            stringBuilder.append("\tItem: ").append(entry.getKey().getName())  // assuming Goods class has a getName() method.
                    .append("\tQuantity: ").append(entry.getValue())
                    .append("\tPrice: ").append(entry.getKey().getPrice()) // assuming Goods class has a getPrice() method.
                    .append("\n");
        }

        stringBuilder.append("Total: ").append(this.total).append("\n");

        return stringBuilder.toString();
    }
}