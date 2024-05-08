package advpro.b2.rasukanbuysell.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.service.ListingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.Builder.ListingBuilder;
//import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.service.CartService;
import advpro.b2.rasukanbuysell.service.ListingService;
//import advpro.b2.rasukanbuysell.service.UserService;

@Controller
@RequestMapping("/Buyer")
public class BuyerController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ListingBuilder listingBuilder;


//    @Autowired
//    private UserService userService;

    @GetMapping("")
    @ResponseBody
    public ModelAndView main(){
        return new ModelAndView("main");
    }

    @GetMapping("/listing/all")
    public ResponseEntity<List<Listing>> getAllListing() {
        List<Listing> listingList = listingService.getAllListings();
        return ResponseEntity.ok(listingList);
    }

    @GetMapping("/listings")
    @ResponseBody
    public ModelAndView getAllListings(Model model) {
        List<Listing> listingList = listingService.getAllListings();
        model.addAttribute("listings", listingList);
        return new ModelAndView("all_listings");
    }


    @GetMapping("/listing/get/{listingId}")
    public ResponseEntity<?> getListing(@PathVariable String listingId) {
        Optional<Listing> listing = listingService.getListing(listingId);
        if (listing.isPresent()) {
            return ResponseEntity.ok(listing.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/listing/create")
    public ResponseEntity<Object> createListing() {
//    public ResponseEntity<Object> createListing(@RequestBody String listingName, @RequestBody int stock, @RequestBody int price) {
        // Use the ListingBuilder to construct a new Listing object
//         Listing newListing = listingBuilder
//                 .reset()
//                 .addName(listingName)
//                 .addStock(stock)
//                 .addId()
//                 .addPrice(price)
//                 .addSeller(UUID.randomUUID()) // masih belom bener
//                 .build();

        
        // Save the new listing using the listing service
//         Listing savedListing = listingService.createListing(newListing);
        
        // dummy data
        Listing listingBaru = new Listing("name", 10, 1000, UUID.randomUUID());
        Listing savedListing = listingService.createListing(listingBaru);

        if (savedListing == null) {
            return new ResponseEntity<>("Ada atribut yang null", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(savedListing);
        }
    }


// udah bisa, yang perlu diganti di service (dummy datanya)
    @PostMapping("/listing/update/{listingId}")
    // public ResponseEntity<Object> update(@RequestBody Listing listing, @PathVariable String listingId) {
    public ResponseEntity<Object> update(@PathVariable String listingId) {
        // data dummy
        Listing listing = new Listing(listingId, "nameUpdated", 69, 6969, UUID.randomUUID());
        
        Listing out = listingService.updateListing(listing);
        if (out == null) {
            return new ResponseEntity<>("null / id not found error " + listing.getListingId(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(out);
    }

    @DeleteMapping("/listing/delete/{listingId}")
    public ResponseEntity<String> deleteListing(@PathVariable String listingId) {
        listingService.deleteListing(listingId);
        return ResponseEntity.ok("successfully deleted");
    }



    @GetMapping("/cart/get/{cartId}")
    public ResponseEntity<Cart> seeCart(@PathVariable String cartId) {
        // Convert the cartId to a UUID
        UUID cartUuid = UUID.fromString(cartId);

        // Get the cart
        Optional<Cart> cartOpt = cartService.getCart(cartUuid);

        // If the cart exists, return it
        return cartOpt.map(ResponseEntity::ok).orElse(null);
    }


    @PostMapping("/cart/add/{cartId}")
    public ResponseEntity<String> addListingToCart(@PathVariable String cartId, @RequestBody Listing listing) {
        try {
            // Convert the cartId to a UUID
            UUID cartUuid = UUID.fromString(cartId);

            // Add the listing to the cart
            cartService.addToCart(cartUuid, listing);

            // If the operation is successful, return a success message
            return ResponseEntity.ok("Listing added to cart successfully.");
        } catch (Exception e) {
            // If there's an error, return an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/cart/remove/{cartId}")
    public ResponseEntity<String> removeListingFromCart(@PathVariable String cartId, @RequestBody Listing listing) {
        try {
            // Convert the cartId to a UUID
            UUID cartUuid = UUID.fromString(cartId);

            // Remove the listing from the cart
            cartService.removeFromCart(cartUuid, listing);

            // If the operation is successful, return a success message
            return ResponseEntity.ok("Listing removed from cart successfully.");
        } catch (Exception e) {
            // If there's an error, return an error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
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
