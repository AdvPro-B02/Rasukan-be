package advpro.b2.rasukanbuysell.model;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.ListingtoCart;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListingtoCartTest {

    @Test
    public void testListingToCartConstructor() {
        Listing listing = new Listing();
        Cart cart = new Cart();
        int quantity = 5;
        ListingtoCart listingToCart = new ListingtoCart();
        listingToCart.setListing(listing);
        listingToCart.setCart(cart);
        listingToCart.setQuantity(quantity);
        assertEquals(listing, listingToCart.getListing());
        assertEquals(cart, listingToCart.getCart());
        assertEquals(quantity, listingToCart.getQuantity());
    }

    @Test
    public void testListingToCartToString() {
        ListingtoCart listingToCart = new ListingtoCart();
        String expectedString = "{\"listingInCartId\":null,\"listing\":null,\"cart\":null,\"quantity\":0}";
        assertEquals(expectedString, listingToCart.toString());
    }
}
