package advpro.b2.rasukanbuysell.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListingTest {
    Listing listing;
    User user;
    Cart cart;

    @BeforeEach
    void setUp(){
        this.listing = new Listing();
        this.user = new User();
        this.cart = new Cart(user);

        this.listing.setListingId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.listing.setName("Item Name");
        this.listing.setPrice(100);
        this.listing.setOwner(user);
        this.listing.setCart(cart);
    }

    @Test
    void testGetListingId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", listing.getListingId());
    }

    @Test
    void testGetName(){
        assertEquals("Item Name", listing.getName());
    }

    @Test
    void testGetPrice(){
        assertEquals(100, listing.getPrice());
    }

    @Test
    void testGetOwner(){
        assertEquals(user, listing.getOwner());
    }

    @Test
    void testGetCart(){
        assertEquals(cart, listing.getCart());
    }
}
