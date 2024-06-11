import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = new Shop("Shop1.json"); // change 'test.json' to your JSON file's path
    }

    @AfterEach
    void tearDown() {
        shop = null;
    }

    @Test
    void testMultipleParameters() {
        assertEquals(10, shop.getNumOfGoodsOnShelf());
        assertEquals(5, shop.getNumOfCashiers());
        assertEquals(70, shop.getPercentEdible());
        assertEquals(30, shop.getPercentNonedible());
    }

    @Test
    void testGoods() {
        Goods goods = shop.getGoodsFromShelf(0);
        assertEquals("Apple", goods.getName());
        assertEquals(0.5, goods.getPrice());
        assertEquals("edible", goods.getCategory().toString().toLowerCase());
    }

    @Test
    void testShopNotNull() {
        assertNotNull(shop, "Shop should not be null after creation");
    }

    @Test
    void testGoodsOnShelf() {
        final int numOfGoods = shop.getNumOfGoodsOnShelf();
        assertTrue(numOfGoods > 0, "Number of Goods on shelf should be greater than 0");

        for(int i = 0; i < numOfGoods; i++) {
            Goods goods = shop.getGoodsFromShelf(i);
            assertNotNull(goods, "Goods object should not be null");
            assertNotNull(goods.getName(), "Goods name should not be null");
            assertTrue(goods.getPrice() > 0, "Goods price should be positive");
            assertNotNull(goods.getCategory(), "Goods category should not be null");
        }
    }

    @Test
    void testGoodsOutOfRange() {
        final int numOfGoods = shop.getNumOfGoodsOnShelf();

        assertThrows(IndexOutOfBoundsException.class, () -> shop.getGoodsFromShelf(-1), "Accessing goods with negative index should throw IndexOutOfBoundsException");
        assertThrows(IndexOutOfBoundsException.class, () -> shop.getGoodsFromShelf(numOfGoods), "Accessing goods beyond shelf size should throw IndexOutOfBoundsException");
    }

    @Test
    void testPercentagesAddTo100() {
        int edible = shop.getPercentEdible();
        int nonEdible = shop.getPercentNonedible();
        assertEquals(100, edible + nonEdible, "Percentages for edible and nonedible goods should add up to 100");
    }
}