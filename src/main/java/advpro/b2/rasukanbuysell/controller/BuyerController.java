package advpro.b2.rasukanbuysell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.service.CartService;
import advpro.b2.rasukanbuysell.service.ListingService;
import advpro.b2.rasukanbuysell.service.UserService;

@Controller
@RequestMapping("/Buyer")
public class BuyerController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/listings/get/{listingId}")
    public ResponseEntity<Listing> getListing(@PathVariable String listingId) {
        Listing listing = listingService.getListing(listingId);
        return ResponseEntity.ok(listing);
    }

    @PostMapping("/cart/add/{userId}")
    public ResponseEntity<String> insertToCart(@PathVariable String userId, String listingId) {
        User user = userService.getUser(userId);
        Listing listing = listingService.getListing(listingId);
        cartService.addToCart(user, listing);
        return ResponseEntity.ok("Listing added to cart");
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getSeller(@PathVariable String userId) {
        User seller = userService.getUser(userId);
        return ResponseEntity.ok(seller);
    }

    @PostMapping("cart/remove/{userId}")
    public ResponseEntity<String> removeListing(@PathVariable String userId, String listingId) {
        User user = userService.getUser(userId);
        Listing listing = listingService.getListing(listingId);
        cartService.removeFromCart(user, listing);
        return ResponseEntity.ok("Listing removed from cart");
    }

    @GetMapping("cart/see/{userId}")
    public ResponseEntity<List<Listing>> seeCart(@PathVariable String userId) {
        User user = userService.getUser(userId);
        List<Listing> cartListings = cartService.getCart(user).getInsideCart();
        return ResponseEntity.ok(cartListings);
    }

    @PostMapping("/cart/checkout/{userId}")
    public ResponseEntity<String> checkout(@PathVariable String userId) {
        User user = userService.getUser(userId);
        cartService.checkout(user);
        return ResponseEntity.ok("Checkout successful");
    }
}
