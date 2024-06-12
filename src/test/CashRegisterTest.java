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
        Cashier cashier = new Cashier("testCashier", 3000);
        Customer customer = new Customer("Tester", "123", 300);
        Date expDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30);
        Goods apple = new Goods("Apple", 1.0, Goods.Category.EDIBLE, expDate, 1000);
        Map<Goods, Integer> goodsSold = new HashMap<>();
        goodsSold.put(apple, 2);
        Receipt receipt = cashRegister.sellGoods(goodsSold, cashier, customer);
        String receiptString = receipt.toString();
        Assertions.assertTrue(receiptString.contains("Apple"));
        Assertions.assertTrue(receiptString.contains("2.0"));
    }

    @Test
    public void testSellGoodsWithNoGoods() {
        Cashier cashier = new Cashier("testCashier", 3000);
        Customer customer = new Customer("Tester", "123", 300);
        Map<Goods, Integer> goodsSold = new HashMap<>();
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, cashier, customer));
    }

    @Test
    public void testSellGoodsWithInsufficientCustomerBalance() {
        Cashier cashier = new Cashier("testCashier", 3000);
        Customer customer = new Customer("Tester", "123", 100);
        Map<Goods, Integer> goodsSold = new HashMap<>();
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, cashier, customer));
    }

    @Test
    public void testSellGoodsWithNullCustomer() {
        Cashier cashier = new Cashier("testCashier", 3000);
        Map<Goods, Integer> goodsSold = new HashMap<>();
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, cashier, null));
    }

    @Test
    public void testSellGoodsWithNullCashier() {
        Customer customer = new Customer("Tester", "123", 100);
        Map<Goods, Integer> goodsSold = new HashMap<>();
        Assertions.assertThrows(Exception.class, () ->
                cashRegister.sellGoods(goodsSold, null, customer));
    }
}