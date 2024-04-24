package advpro.b2.rasukanbuysell.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(User user) {
        return cartRepository.create(user);
    }

    @Override
    public Cart getCart(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public void addToCart(User user, Listing listing) {
        Cart cart = getCart(user);
        if (cart != null) {
            List<Listing> insideCart = cart.getInsideCart();
            insideCart.add(listing);
            updateCart(user, insideCart);
        }
    }


    @Override
    public Cart updateCart(User user, List<Listing> updatedInsideCart) {
        return cartRepository.updateCart(user, updatedInsideCart);
    }

    @Override
    public Cart removeFromCart(User user, Listing listing) {
        Cart cart = getCart(user);
        if (cart != null) {
            List<Listing> insideCart = cart.getInsideCart();
            insideCart.remove(listing);
            cart = updateCart(user, insideCart);
        }
        return cart;
    }

    // masih perlu tambahin banyak
    @Override
    public void checkout(User user) {
        Cart cart = getCart(user);
        if (cart != null) {
            cart.setInsideCart(new ArrayList<>());
            updateCart(user, cart.getInsideCart());
        }
    }
}
