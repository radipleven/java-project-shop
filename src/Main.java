import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // manual or automated shop
        System.out.println("Select mode of operation:");
        System.out.println("1. Manual");
        System.out.println("2. Automated");
        System.out.println("Enter your choice (1 or 2):");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            // manual
            case 1:
                // Create shop
                Shop shop = new Shop("Shop1.json");
                Cashier iskra = new Cashier("Iskra Fidosova", 5000);
                // Display basic shop details
                System.out.println("Welcome to our Shop!");
                System.out.printf("We have %d goods on our shelves:\n", shop.getNumOfGoodsOnShelf());

                // List all goods on shelf
                for (int i = 0; i < shop.getNumOfGoodsOnShelf(); i++) {
                    Goods good = shop.getGoodsFromShelf(i);
                    System.out.printf("%d. %s \n", i, good.getName());
                }

                System.out.printf("%d%% of our goods are edible, and %d%% are non-edible.\n", shop.getPercentEdible(), shop.getPercentNonedible());

                // Interact with the user
                while (true) {
                    System.out.println("Enter the index of the good you're interested in (or 'q' to quit):");
                    String input = scanner.nextLine();

                    if ("q".equalsIgnoreCase(input)) {
                        break;
                    }

                    try {
                        int index = Integer.parseInt(input);
                        Goods good = shop.getGoodsFromShelf(index);
                        System.out.printf("You selected: %s - Price: %f, Category: %s\n", good.getName(), good.getPrice(), good.getCategory());

                        // Create a new Receipt for the purchase
                        Map<Goods, Integer> goodsSold = new HashMap<>();
                        goodsSold.put(good, 1); // 1 item bought for simplicity
                        Receipt receipt = new Receipt(iskra, goodsSold, good.getPrice()); // Replace with actual Cashier if necessary

                        System.out.println("Receipt for your purchase: ");
                        System.out.println(receipt.toString());

                    } catch (NumberFormatException nfe) {
                        System.err.println("Invalid input. Please enter a number.");
                    } catch (IndexOutOfBoundsException iobe) {
                        System.err.println("Invalid index. Please choose an index within the range of goods on the shelves.");
                    }
                }
                System.out.println("Total receipts issued so far: " + Receipt.getTotalReceipts());
                System.out.println("Total turnover so far: " + Receipt.getTotalTurnover());
                System.out.println("Thank you for visiting our shop!");

                break;

            case 2:
                // create shop
                shop = new Shop("Shop1.json");

                // create customers
                Customer[] customers = {
                        new Customer("Delyan Peevski", "DP-900", 9000.0),
                        new Customer("Boiko Borisov", "BB-100", 1000.0),
                        new Customer("Desislava Atanasova", "DA-200", 1000.0),
                        new Customer("Kiril Petkov", "KP-70", 700.0)
                };

                // create a cashier
                Cashier cashier = new Cashier("Tsvetan Vasilev", 2000.0);

                // each customer enters the shop, interacts with the cashier, buys goods, and then leaves
                for (Customer customer : customers) {
                    System.out.printf("\n%s enters the shop.\n", customer.getName());

                    // customer selects random goods from the shop
                    Map<Goods, Integer> selectedGoods = new HashMap<>();
                    int numOfGoods = shop.getNumOfGoodsOnShelf();
                    Random random = new Random();
                    // random one to three of each good
                    int selectGoodQuantity = random.nextInt(3) + 1;

                    for (int i = 0; i < selectGoodQuantity; i++ ) {
                        int selectedGoodIndex = random.nextInt(numOfGoods);
                        Goods good = shop.getGoodsFromShelf(selectedGoodIndex);
                        System.out.printf("%s selects %s.\n", customer.getName(), good.getName());
                        selectedGoods.put(good, 1); // simulate customer select 1 quantity of each good
                    }

                    System.out.printf("%s goes to %s\n", customer.getName(), cashier.getName());
                    Receipt receipt = cashier.sellGoods(selectedGoods, customer);
                    System.out.printf("%s buys goods for a total cost of %f.\n", customer.getName(), receipt.getTotal());

                    // save recipt to file
                    receipt.saveToFile();

                    //customer leaves
                    System.out.printf("%s leaves the shop.\n", customer.getName());
                }
                System.out.println("All customers have left the shop.\n");
                System.out.println("Total receipts issued so far: " + Receipt.getTotalReceipts());
                System.out.println("Total turnover so far: " + Receipt.getTotalTurnover());
                break;

                //if user does not properly select manual ot automated
            default:
                System.out.println("Invalid input! Please enter 1 or 2.");
                break;
        }
    }
}
