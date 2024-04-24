package advpro.b2.rasukanbuysell.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;

class CartRepositoryTest {
    CartRepository cartRepository;
    
    @BeforeEach
    void setUp() {
        cartRepository = new CartRepository();
    }

    @Test
    void testCreate() {
        User user = new User();
        user.setUserId("123");
        user.setName("Test User");

        Cart createdCart = cartRepository.create(user);

        assertNotNull(createdCart);
        assertEquals(user, createdCart.getOwner());
    }

    @Test
    void testFindByUser() {
        User user = new User();
        user.setUserId("123");
        user.setName("Test User");

        cartRepository.create(user);

        Cart foundCart = cartRepository.findByUser(user);

        assertNotNull(foundCart);
        assertEquals(user, foundCart.getOwner());
    }

    @Test
    void testUpdateCart() {
        User user = new User();
        user.setUserId("123");
        user.setName("Test User");

        cartRepository.create(user);

        List<Listing> updatedInsideCart = new ArrayList<>();
        Listing listing = new Listing();
        listing.setListingId("456");
        listing.setName("Test Listing");
        listing.setPrice(100);
        updatedInsideCart.add(listing);

        Cart updatedCart = cartRepository.updateCart(user, updatedInsideCart);

        assertNotNull(updatedCart);
        assertEquals(updatedInsideCart, updatedCart.getInsideCart());
    }
}
