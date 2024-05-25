import advpro.b2.rasukanbuysell.model.Listing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListingTest {

    @Test
    public void testListingConstructor() {
        String name = "Test Listing";
        int stock = 10;
        int price = 100;
        String seller = "seller123";
        Listing listing = new Listing(name, stock, price, seller);
        assertEquals(name, listing.getName());
        assertEquals(stock, listing.getStock());
        assertEquals(price, listing.getPrice());
        assertEquals(seller, listing.getSeller());
        assertEquals(0, listing.getOrderCounter());
    }

    @Test
    public void testListingToString() {
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");
        String expectedString = "{\"listingId\":null,\"name\":\"Test Listing\",\"price\":100,\"stock\":10,\"seller\":\"seller123\",\"orderCounter\":0}";
        assertEquals(expectedString, listing.toString());
    }
}
