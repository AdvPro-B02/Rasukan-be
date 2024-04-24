package advpro.b2.rasukanbuysell.service;

import java.util.List;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;

public interface CartService {

    Cart createCart(User user);

    Cart getCart(User user);

    void addToCart(User user, Listing listing);

    Cart updateCart(User user, List<Listing> updatedInsideCart);

    Cart removeFromCart(User user, Listing listing);

    void checkout(User user);
}
