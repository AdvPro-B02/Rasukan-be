package advpro.b2.rasukanbuysell.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.User;
//import advpro.b2.rasukanbuysell.service.CartService;
import advpro.b2.rasukanbuysell.service.ListingService;
//import advpro.b2.rasukanbuysell.service.UserService;

@Controller
@RequestMapping("/Buyer")
public class BuyerController {

    @Autowired
    private ListingService listingService;

//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private UserService userService;

    @RequestMapping("")
    public String main(){
        return "main";
    }

    @GetMapping("/listing")
    public ResponseEntity<List<Listing>> getAllListing() {
        List<Listing> listingList = listingService.getAllListings();
        return ResponseEntity.ok(listingList);
    }

    @GetMapping("/listings/get/{listingId}")
    public ResponseEntity<?> getListing(@PathVariable String listingId) {
        Optional<Listing> listing = listingService.getListing(listingId);
        if (listing.isPresent()) {
            return ResponseEntity.ok(listing.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/listing/create")
    public ResponseEntity<Object> createListing(@RequestBody Listing listing) {
        Listing newListing = listingService.createListing(listing);

        if (newListing == null) {
            return new ResponseEntity<>("Ada atribut yang null", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(newListing);
        }
    }


    @PostMapping("/listing/save")
    public ResponseEntity<Object> save(@RequestBody Listing listing) {
        Listing out = listingService.updateListing(listing);
        if (out == null) {
            return new ResponseEntity<>("null / id not found error" + listing.getListingId(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(out);
    }

    @DeleteMapping("/listing/{id}")
    public ResponseEntity<String> deleteListing(@PathVariable String listingId) {
        listingService.deleteListing(listingId);
        return ResponseEntity.ok("successfully deleted");
    }


//    @PostMapping("/cart/add/{userId}")
//    public ResponseEntity<String> insertToCart(@PathVariable String userId, String listingId) {
//        User user = userService.getUser(userId);
//        Listing listing = listingService.getListing(listingId);
//        cartService.addToCart(user, listing);
//        return ResponseEntity.ok("Listing added to cart");
//    }

//    @GetMapping("/profile/{userId}")
//    public ResponseEntity<User> getSeller(@PathVariable String userId) {
//        User seller = userService.getUser(userId);
//        return ResponseEntity.ok(seller);
//    }

//    @PostMapping("cart/remove/{userId}")
//    public ResponseEntity<String> removeListing(@PathVariable String userId, String listingId) {
//        User user = userService.getUser(userId);
//        Listing listing = listingService.getListing(listingId);
//        cartService.removeFromCart(user, listing);
//        return ResponseEntity.ok("Listing removed from cart");
//    }

//    @GetMapping("cart/see/{userId}")
//    public ResponseEntity<List<Listing>> seeCart(@PathVariable String userId) {
//        User user = userService.getUser(userId);
//        List<Listing> cartListings = cartService.getCart(user).getInsideCart();
//        return ResponseEntity.ok(cartListings);
//    }

//    @PostMapping("/cart/checkout/{userId}")
//    public ResponseEntity<String> checkout(@PathVariable String userId) {
//        User user = userService.getUser(userId);
//        cartService.checkout(user);
//        return ResponseEntity.ok("Checkout successful");
//    }
}
