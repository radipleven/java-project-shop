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

        assertEquals(validName, customer.getName());
        assertEquals(validId, customer.getId());
        assertEquals(initialBalance, customer.getBalance());
        assertTrue(customer.getCart().isEmpty());
    }

    @Test
    void testAddGoodsToCart() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        Goods goods1 = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);

        customer.addGoodsToCart(goods1, 2);

        assertTrue(customer.getCart().containsKey(goods1));
        assertEquals(2, customer.getCart().get(goods1).intValue());
    }

    @Test
    void testSubtractBalance() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        double amountToSub = 50.0;

        customer.subtractBalance(amountToSub);

        assertEquals(50.0, customer.getBalance());
    }

    @Test
    void testIsCartEmpty() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        assertTrue(customer.isCartEmpty());

        Goods goods = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);
        customer.addGoodsToCart(goods, 1);
        assertFalse(customer.isCartEmpty());
    }

    @Test
    void testRemoveGoodsFromCart() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        Goods goods = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);

        customer.addGoodsToCart(goods, 5);
        assertEquals(Integer.valueOf(5), customer.getCart().get(goods));

        customer.removeGoodsFromCart(goods, 2);
        assertEquals(Integer.valueOf(3), customer.getCart().get(goods));

        customer.removeGoodsFromCart(goods, 3);
        assertFalse(customer.getCart().containsKey(goods));
    }

    @Test
    void testGetQuantityInCart() {
        Customer customer = new Customer("Customer1", "Cust1", 100.0);
        Goods goods1 = new Goods("Goods1", 20.0, Goods.Category.EDIBLE, new Date(), 10);
        Goods goods2 = new Goods("Goods2", 30.0, Goods.Category.EDIBLE, new Date(), 5);

        customer.addGoodsToCart(goods1, 5);
        assertEquals(Integer.valueOf(5), customer.getQuantityInCart(goods1));
        assertEquals(Integer.valueOf(0), customer.getQuantityInCart(goods2));
    }
}