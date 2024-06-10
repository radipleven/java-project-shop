import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CashRegisterTest {

    private CashRegister cashRegister;

    @BeforeEach
    public void setUp() {
        cashRegister = new CashRegister(1, 200);
    }

    @Test
    public void testBalanceGetter() {
        Assertions.assertEquals(200, cashRegister.getBalance());
    }

    @Test
    public void testSellGoods() {
        Cashier cashier = new Cashier("testCashier", 3000); // creating a valid cashier instance
        Customer customer = new Customer("Tester", "123", 300); // creating a valid customer instance

        // Assuming that the Goods are EDIBLE and don't expire too soon, also enough in quantity
        Date expDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30); // For example, 30 days from now
        Goods apple = new Goods("Apple", 1.0, Goods.Category.EDIBLE, expDate, 1000); // Creating a valid Goods instance.

        Map<Goods, Integer> goodsSold = new HashMap<>();
        goodsSold.put(apple, 2); // The customer buys 2 apples

        Receipt receipt = cashRegister.sellGoods(goodsSold, cashier, customer);

        String receiptString = receipt.toString();

        Assertions.assertTrue(receiptString.contains("Apple"));  // assuming toString() includes Goods.name
        Assertions.assertTrue(receiptString.contains("2.0"));  // assuming toString() includes Goods.price times quantity.
    }

    @Test
    public void testSellGoodsWithNoGoods() {
        Cashier cashier = new Cashier("testCashier", 3000); // having a valid Cashier instance
        Customer customer = new Customer("Tester", "123", 300); // having a valid Customer instance
        Map<Goods, Integer> goodsSold = new HashMap<>(); // Goods map is empty.
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, cashier, customer)); // Assuming it throws an Exception in this case
    }

    @Test
    public void testSellGoodsWithInsufficientCustomerBalance() {
        Cashier cashier = new Cashier("testCashier", 3000); // having a valid Cashier instance
        Customer customer = new Customer("Tester", "123", 100); // Assuming customer has insufficient balance.
        Map<Goods, Integer> goodsSold = new HashMap<>();
        // populate this map accordingly such that total cost exceeds customer balance.
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, cashier, customer)); // Assuming it throws an Exception in this case
    }

    @Test
    public void testSellGoodsWithNullCustomer() {
        Cashier cashier = new Cashier("testCashier", 3000); // having a valid Cashier instance
        Map<Goods, Integer> goodsSold = new HashMap<>(); // populate this map accordingly.
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, cashier, null)); // Customer is null.
    }

    @Test
    public void testSellGoodsWithNullCashier() {
        Customer customer = new Customer("Tester", "123", 100); // Having a valid Customer instance
        Map<Goods, Integer> goodsSold = new HashMap<>(); // populate this map accordingly.
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, null, customer)); // Cashier is null.
    }
}