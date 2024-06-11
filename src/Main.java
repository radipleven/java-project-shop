import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Ask the user for the mode of operation
        System.out.println("Select mode of operation:");
        System.out.println("1. Manual");
        System.out.println("2. Automated");
        System.out.println("Enter your choice (1 or 2):");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                // Create a new shop
                Shop shop = new Shop("Shop1.json"); // insert the appropriate path to your JSON file

                // Initialize the scanner for user input


                // Display basic shop details
                System.out.println("Welcome to our Shop!");
                System.out.printf("We have %d goods on our shelves:\n", shop.getNumOfGoodsOnShelf());

                // List all goods on shelf
                for (int i = 0; i < shop.getNumOfGoodsOnShelf(); i++) {
                    Goods good = shop.getGoodsFromShelf(i);
                    System.out.printf("%d. %s \n", i, good.getName());
                }

                System.out.printf("%d%% of our goods are edible, and %d%% are non-edible.\n", shop.getPercentEdible(), shop.getPercentNonedible());

                // Interact with the user to demonstrate fetching goods details
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
                    } catch (NumberFormatException nfe) {
                        System.err.println("Invalid input. Please enter a number.");
                    } catch (IndexOutOfBoundsException iobe) {
                        System.err.println("Invalid index. Please choose an index within the range of goods on the shelves.");
                    }
                }

                System.out.println("Thank you for visiting our shop!");

                break;

            case 2:
                // Create a new shop
                shop = new Shop("Shop1.json");

                // Create customers
                Customer[] customers = {
                        new Customer("Delyan Peevski", "DP-900", 9000.0),
                        new Customer("Boiko Borisov", "BB-100", 1000.0),
                        new Customer("Kiril Petkov", "KP-70", 700.0)
                };

                // Create a cashier
                Cashier cashier = new Cashier("Kasier Kasierov", 2000.0);

                // Each customer enters the shop, interacts with the cashier, buys goods, and then leaves
                for (Customer customer : customers) {
                    System.out.printf("\n%s enters the shop.\n", customer.getName());

                    // Customer selects random goods from the shop
                    Map<Goods, Integer> selectedGoods = new HashMap<>();
                    int numOfGoods = shop.getNumOfGoodsOnShelf();
                    Random random = new Random();
                    int selectGoodQuantity = random.nextInt(3) + 1; // select 1 to 3 types of goods

                    for (int i = 0; i < selectGoodQuantity; i++ ) {
                        int selectedGoodIndex = random.nextInt(numOfGoods);
                        Goods good = shop.getGoodsFromShelf(selectedGoodIndex);
                        System.out.printf("%s selects %s.\n", customer.getName(), good.getName());
                        selectedGoods.put(good, 1); // simulate customer select 1 quantity of each good
                    }

                    System.out.printf("%s goes to %s\n", customer.getName(), cashier.getName());
                    Receipt receipt = cashier.sellGoods(selectedGoods, customer);
                    System.out.printf("%s buys goods for a total cost of %f.\n", customer.getName(), receipt.getTotal());

                    // Save the receipt to file
                    receipt.saveToFile();

                    // Customer leaves
                    System.out.printf("%s leaves the shop.\n", customer.getName());
                }
                System.out.println("All customers have left the shop.");
                break;

            default:
                System.out.println("Invalid input! Please enter 1 or 2.");
                break;
        }
    }
}
