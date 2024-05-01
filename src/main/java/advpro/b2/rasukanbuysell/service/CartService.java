package advpro.b2.rasukanbuysell.service;

import java.util.List;
import java.util.UUID;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;


public interface CartService {

    Cart createCart(UUID user);

    Cart getCart(UUID user);

    void addToCart(UUID user, Listing listing);

    Cart updateCart(UUID user, List<Listing> updatedInsideCart);

    Cart removeFromCart(UUID user, Listing listing);

    void checkout(UUID user);
}
