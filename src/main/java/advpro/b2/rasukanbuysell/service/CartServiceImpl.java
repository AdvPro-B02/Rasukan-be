package advpro.b2.rasukanbuysell.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart createCart(UUID user) {
        return new Cart();
//      return cartRepository.create(user);
    }

    @Override
    public Cart getCart(UUID user) {
        return new Cart();
//        return cartRepository.findByUser(user);
    }

    @Override
    public void addToCart(UUID user, Listing listing) {
//        Cart cart = getCart(user);
//        if (cart != null) {
//            List<Listing> insideCart = cart.getInsideCart();
//            insideCart.add(listing);
//            updateCart(user, insideCart);
//        }
    }


    @Override
    public Cart updateCart(UUID user, List<Listing> updatedInsideCart) {
        return new Cart();
//        return cartRepository.updateCart(user, updatedInsideCart);
    }

    @Override
    public Cart removeFromCart(UUID user, Listing listing) {
        Cart cart = getCart(user);
//        if (cart != null) {
//            List<Listing> insideCart = cart.getInsideCart();
//            insideCart.remove(listing);
//            cart = updateCart(user, insideCart);
//        }
        return cart;
    }

    // masih perlu tambahin banyak
    @Override
    public void checkout(UUID user) {
        Cart cart = getCart(user);
        if (cart != null) {
//            cart.setInsideCart(new ArrayList<>());
//            updateCart(user, cart.getInsideCart());
        }
    }
}
