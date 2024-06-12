import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;


class GoodsTest {

    @Test
    void testConstructor_validInput() {
        String expectedName = "ValidName";
        double expectedPrice = 30.0;
        Goods.Category expectedCategory = Goods.Category.EDIBLE;
        Date expectedDate = new Date();
        int expectedQuantity = 5;

        Goods goods = new Goods(expectedName, expectedPrice, expectedCategory, expectedDate, expectedQuantity);

        assertEquals(expectedName, goods.getName());
        assertEquals(expectedPrice, goods.getPrice());
        assertEquals(expectedCategory.name(), goods.getCategory());
        assertEquals(expectedDate, goods.getExpDate());
        assertEquals(expectedQuantity, goods.getQuantityAvailable());
    }

    @Test
    void testConstructor_invalidName() {
        String invalidName = "";
        double expectedPrice = 30.0;
        Goods.Category expectedCategory = Goods.Category.EDIBLE;
        Date expectedDate = new Date();
        int expectedQuantity = 5;

        assertThrows(IllegalArgumentException.class, () -> {
            Goods goods = new Goods(invalidName, expectedPrice, expectedCategory, expectedDate, expectedQuantity);
        }, "Expected IllegalArgumentException to be thrown, but it didn't.");
    }

    @Test
    void testConstructor_nonPositivePrice() {
        String validName = "ValidName";
        double nonPositivePrice = 0.0; // adjust to a non-positive value
        Goods.Category validCategory = Goods.Category.EDIBLE;
        Date validDate = new Date();
        int validQuantity = 5;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Goods(validName, nonPositivePrice, validCategory, validDate, validQuantity);
        });

        String expectedMessage = "Price must be greater than 0";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage),
                "Expected IllegalArgumentException with message about non-positive price, but did not get it.");
    }

    @Test
    void testConstructor_categoryAndExpDate() {
        String validName = "ValidName";
        double positivePrice = 30.0;
        Goods.Category validCategory = Goods.Category.EDIBLE;
        Date date = new Date();
        int validQuantity = 5;

        Goods goods = new Goods(validName, positivePrice, validCategory, date, validQuantity);

        // testing 'category' field
        assertEquals(goods.getCategory(), "EDIBLE", "Category is not assigned correctly in Goods constructor");

        // testing 'expDate' field
        assertEquals(goods.getExpDate(), date, "ExpDate is not assigned correctly in Goods constructor");
    }
}