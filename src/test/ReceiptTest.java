import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    private Receipt receipt;
    private Cashier worker;
    private Goods goods;
    private Map<Goods, Integer> goodsSold;

    @BeforeEach
    public void setUp() {
        worker = new Cashier("John", 5000.0);

        // initialize 'goods' with Goods.Category.EDIBLE
        // 'goods' is declared at the class scope
        goods = new Goods("Apple", 2.5, Goods.Category.EDIBLE, new Date(System.currentTimeMillis()), 10);

        goodsSold = new HashMap<>();
        goodsSold.put(goods, 5);

        receipt = new Receipt(worker, goodsSold, 20.0);
    }

    @Test
    public void testGetId() {
        assertTrue(receipt.getId() > 0);
    }

    @Test
    public void testGetWorker() {
        assertEquals(worker, receipt.getWorker());
    }

    @Test
    public void testGetDateTimeOfCreation() {
        LocalDateTime now = LocalDateTime.now();
        assertEquals(now.getYear(), receipt.getDateTimeOfCreation().getYear());
        assertEquals(now.getMonth(), receipt.getDateTimeOfCreation().getMonth());
        assertEquals(now.getDayOfMonth(), receipt.getDateTimeOfCreation().getDayOfMonth());
    }

    @Test
    public void testGetGoodsSold() {
        assertTrue(receipt.getGoodsSold().containsKey(goods));
        assertEquals(5, receipt.getGoodsSold().get(goods));
    }

    @Test
    public void testGetTotal() {
        assertEquals(20.0, receipt.getTotal());
    }

    @Test
    public void testToString() {
        String receiptString = receipt.toString();
        assertTrue(receiptString.contains(worker.getName()));
        assertTrue(receiptString.contains(goods.getName()));
        assertTrue(receiptString.contains(String.valueOf(goods.getPrice())));
        assertTrue(receiptString.contains(String.valueOf(goodsSold.get(goods))));
    }
}