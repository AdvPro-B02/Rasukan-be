package advpro.b2.rasukanbuysell.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    Cart cart;
    User user;
    Listing listing;

    @BeforeEach
    void setUp(){
        this.cart = new Cart();
        this.user = new User();
        this.listing = new Listing();

        List<Listing> insideCart = new ArrayList<>();
        insideCart.add(listing);

        this.cart.setOwner(user);
        this.cart.setInsideCart(insideCart);
    }

    @Test
    void testGetOwner(){
        assertEquals(user, cart.getOwner());
    }

    @Test
    void testGetInsideCart(){
        assertTrue(cart.getInsideCart().contains(listing));
    }
}
