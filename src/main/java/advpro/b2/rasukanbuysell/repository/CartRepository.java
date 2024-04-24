package advpro.b2.rasukanbuysell.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;

@Repository
public class CartRepository {

    private static List<Cart> carts = new ArrayList<>();

    public Cart create(User user) {
        Cart cart = new Cart(user);
        carts.add(cart);
        return cart;
    }

    public Cart findByUser(User user) {
        for (Cart cart : carts) {
            if (cart.getOwner().equals(user)) {
                return cart;
            }
        }
        return null;
    }

    public Cart updateCart(User user, List<Listing> updatedInsideCart) {
        Cart cart = findByUser(user);
        if (cart != null) {
            cart.setInsideCart(updatedInsideCart);
        }
        return cart;
    }
}
