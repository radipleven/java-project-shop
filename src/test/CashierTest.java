import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class CashierTest {

    private Cashier cashier;

    @BeforeEach
    public void setUp() {
        cashier = new Cashier("testCashier", 3000);
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
        CashRegister cashRegister = new CashRegister(1,120.75); // create an instance with suitable parameters
        cashier.assignRegister(cashRegister);
        // validate - you should have a getter for assignedRegister or use reflection
    }

    @Test
    public void testSellGoods() {
        Map<Goods, Integer> goodsSold = new HashMap<>(); // populate this map accordingly
        Customer customer = new Customer("Tester", "123", 245.12); // create a customer instance
        Receipt receipt = cashier.sellGoods(goodsSold, customer);
        // validate - you should have some getters on Receipt or use some predefined receipts and validate against them
    }

    @Test
    public void testCalculateTotalCost() {
        Map<Goods, Integer> goodsSold = new HashMap<>(); // populate this map accordingly
        double total = cashier.calculateTotalCost(goodsSold);
        // validate - depends on the list of goods passed and their cost
    }
}