import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CashierTest {

    private Cashier cashier;
    private CashRegister cashRegister;
    private Map<Goods, Integer> goodsSold;

    @BeforeEach
    public void setUp() {
        cashier = new Cashier("testCashier", 3000);
        cashRegister = new CashRegister(1,120.75);
        cashier.assignRegister(cashRegister);

        Date todaysDate = new Date();
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE, 7);
        Date expirationDate = c.getTime();  // 1 week from now

        Goods apple = new Goods("Apple", 1.50, Goods.Category.EDIBLE, expirationDate, 100);
        Goods bread = new Goods("Bread", 0.80, Goods.Category.EDIBLE, expirationDate, 50);
        goodsSold = new HashMap<>();
        goodsSold.put(apple, 3);
        goodsSold.put(bread, 2);
    }

    @Test
    public void testNameGetter() {
        Assertions.assertEquals("testCashier", cashier.getName());
    }

    @Test
    public void testSalaryGetter() {
        Assertions.assertEquals(3000, cashier.getSalary());
    }

    @Test
    public void testAssignRegister() {
        cashier.assignRegister(cashRegister);
        CashRegister assignedRegister = cashier.getAssignedRegister();
        Assertions.assertSame(cashRegister, assignedRegister,
                "assigned register should equal created register");
    }

    @Test
    public void testSellGoods() {
        Customer customer = new Customer("Tester", "123", 245.12); // create a customer instance
        Receipt receipt = cashier.sellGoods(goodsSold, customer);

        Assertions.assertSame(cashier, receipt.getWorker(),
                "Cashier who sold goods must be the same as cashier in receipt");
        Assertions.assertEquals(goodsSold, receipt.getGoodsSold(),
                "Goods sold should be the same in receipt");
        // calculate the total cost for purchased goods
        double totalCost = cashier.calculateTotalCost(goodsSold);
        Assertions.assertEquals(totalCost, receipt.getTotal(),
                "Total cost should be equal to the sum of costs in receipt");
    }

    @Test
    public void testCalculateTotalCost() {
        double total = cashier.calculateTotalCost(goodsSold);
        double expectedTotal = goodsSold.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue()) // compute cost of each item
                .sum(); // sum them up

        Assertions.assertEquals(expectedTotal, total, "Calculated total cost should match the expected total");
    }
}