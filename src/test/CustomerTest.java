import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void testConstructor() {
        String validName = "Customer1";
        String validId = "Cust1";
        double initialBalance = 100.0;

        Customer customer = new Customer(validName, validId, initialBalance);

        assertEquals(validName, customer.getName(), "Name was not correctly assigned in Customer constructor");
        assertEquals(validId, customer.getId(), "Id was not correctly assigned in Customer constructor");
        assertEquals(initialBalance, customer.getBalance(), "Initial Balance was not correctly assigned in Customer constructor");
        assertTrue(customer.getCart().isEmpty(), "Cart should be empty on new Customer instance");
    }

    @Test
    void testAddGoodsToCart() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        Goods goods1 = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);

        customer.addGoodsToCart(goods1, 2);

        assertTrue(customer.getCart().containsKey(goods1), "Cart should contain good after adding it");
        assertEquals(2, customer.getCart().get(goods1).intValue(), "Quantity of added good in cart should be correct");
    }

    @Test
    void testSubtractBalance() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        double amountToSub = 50.0;

        customer.subtractBalance(amountToSub);

        assertEquals(50.0, customer.getBalance(), "method subtractBalance should correctly reduce the balance");
    }
    @Test
    void testIsCartEmpty() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        assertTrue(customer.isCartEmpty(), "Cart should be empty after customer creation");

        Goods goods = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);
        customer.addGoodsToCart(goods, 1);
        assertFalse(customer.isCartEmpty(), "Cart should not be empty after adding goods");
    }

    @Test
    void testRemoveGoodsFromCart() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        Goods goods = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);

        customer.addGoodsToCart(goods, 5);
        assertEquals(Integer.valueOf(5), customer.getCart().get(goods), "5 goods should be added to the cart");

        customer.removeGoodsFromCart(goods, 2);
        assertEquals(Integer.valueOf(3), customer.getCart().get(goods), "2 goods should be removed from the cart");

        customer.removeGoodsFromCart(goods, 3);
        assertFalse(customer.getCart().containsKey(goods), "Goods should be removed completely from the cart");
    }

    @Test
    void testGetQuantityInCart() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        Goods goods1 = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);
        Goods goods2 = new Goods("Goods2", 30.0, Goods.Category.EDIBLE, new Date(), 5);

        customer.addGoodsToCart(goods1, 5);
        assertEquals(Integer.valueOf(5), customer.getQuantityInCart(goods1), "Quantity of Goods1 in the cart should be 5");
        assertEquals(Integer.valueOf(0), customer.getQuantityInCart(goods2), "Quantity of Goods2 in the cart should be 0");
    }
}