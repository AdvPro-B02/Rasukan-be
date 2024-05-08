package advpro.b2.rasukanbuysell.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import advpro.b2.rasukanbuysell.model.ListingtoCart;
import advpro.b2.rasukanbuysell.repository.ListingtoCartRepository;
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

    @Autowired
    private ListingtoCartRepository listingToCartRepository;

    @Override
    public Cart createCart(UUID user) {
        // Check if a Cart with the given UUID already exists
        Optional<Cart> existingCart = cartRepository.findById(user);

        // If the Cart does not exist, create a new one
        if (existingCart.isEmpty()) {
            return cartRepository.save(new Cart(user));
        }
        // If the Cart already exists, return null
        return null;
    }


    @Override
    public Optional<Cart> getCart(UUID user) {
        return cartRepository.getOwner(user);
    }


    @Override
    public void addToCart(UUID user, Listing listing) {
        // Get the Cart of the user
        Optional<Cart> cartOpt = getCart(user);

        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();

            // Create a new ListingtoCart
            ListingtoCart listingToCart = new ListingtoCart();
            listingToCart.setListing(listing);
            listingToCart.setCart(cart);
            listingToCart.setUser(user);
            listingToCart.setQuantity(1); // Set the quantity as needed

            // Save the ListingtoCart to the repository
            listingToCartRepository.save(listingToCart);
        }
    }



//    @Override
//    public Cart updateCart(UUID user, List<Listing> updatedInsideCart) {
//        return new Cart();
////        return cartRepository.updateCart(user, updatedInsideCart);
//    }

    @Override
    public Cart removeFromCart(UUID user, Listing listing) {
        // Get the Cart of the user
        Optional<Cart> cartOpt = getCart(user);

        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();

            // Find the ListingtoCart that maps the Cart and the Listing
            Optional<ListingtoCart> listingToCartOpt = Optional.ofNullable(listingToCartRepository.findListingInCart(cart, listing));

            // Remove the ListingtoCart from the repository
            listingToCartOpt.ifPresent(listingtoCart -> listingToCartRepository.delete(listingtoCart));
        }

        return cartOpt.orElse(null);
    }


    // masih perlu tambahin banyak
    @Override
    public void checkout(UUID user) {
//        Cart cart = getCart(user);
//        if (cart != null) {
//            cart.setInsideCart(new ArrayList<>());
//            updateCart(user, cart.getInsideCart());
//        }
    }
}
