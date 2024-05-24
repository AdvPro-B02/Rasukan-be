package advpro.b2.rasukanbuysell.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.ListingtoCart;
import advpro.b2.rasukanbuysell.repository.CartRepository;
import advpro.b2.rasukanbuysell.repository.ListingRepository;
import advpro.b2.rasukanbuysell.repository.ListingtoCartRepository;
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
    @Autowired
    private ListingtoCartRepository listingtoCartRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ListingRepository listingRepository;


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

    @GetMapping("/listings/by_seller/{sellerId}")
    public ResponseEntity<List<Listing>> findAllListingsBySeller(@PathVariable String sellerId) {
        List<Listing> listings = listingRepository.findAllListingsBySeller(sellerId);
        if (!listings.isEmpty()) {
            return ResponseEntity.ok(listings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/cart/get_listings/{ownerId}")
    public ResponseEntity<?> getListings(@PathVariable String ownerId) {
        Optional<Cart> cart = cartService.getCart(ownerId);
        if (cart.isPresent()) {
            List<Listing> listings = listingtoCartRepository.getAllListingInCart(cartRepository.findByOwnerId(ownerId));
            return ResponseEntity.ok(listings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/listing/create")
//    public ResponseEntity<Object> createListing(@RequestBody Listing listingBaru) {
    public ResponseEntity<Object> createListing(@RequestBody Listing listingBaru, @RequestHeader ("Authorization") String token) {
        // Use the ListingBuilder to construct a new Listing object

        if (token!=null && !token.isEmpty()) {
            Listing newListing = listingService.buildListing(listingBaru, token);


            // Save the new listing using the listing service
             Listing savedListing = listingService.createListing(newListing, token);

            // dummy data, udah gaperlu :D
    //        Listing listingBaru = new Listing("name", 10, 1000, UUID.randomUUID());
    //        Listing savedListing = listingService.createListing(listingBaru);

            if (savedListing == null) {
                return new ResponseEntity<>("Ada atribut yang null", HttpStatus.BAD_REQUEST);
            } else {
                return ResponseEntity.ok(savedListing);
            }

        }
        return ResponseEntity.notFound().build();

    }


// udah bisa, yang perlu diganti di service (dummy datanya)
    @PostMapping("/listing/update/{listingId}")
    public ResponseEntity<Object> update(@PathVariable String listingId, @RequestBody Listing dataListingBaru, @RequestHeader ("Authorization") String token) {

        if (token!=null && !token.isEmpty()) {
            System.out.println(dataListingBaru);
            // data dummy
            Listing listing = new Listing(listingId, dataListingBaru.getName(), dataListingBaru.getPrice(), dataListingBaru.getStock(), dataListingBaru.getSeller());

            Listing out = listingService.updateListing(listing);
            if (out == null) {
                return new ResponseEntity<>("null / id not found error " + listing.getListingId(), HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(out);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/listing/delete/{listingId}")
    public ResponseEntity<String> deleteListing(@PathVariable String listingId, @RequestHeader ("Authorization") String token) {

        if (token != null && !token.isEmpty()) {
            listingService.deleteListing(listingId);
            return ResponseEntity.ok("successfully deleted");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cart/from_seller/{userId}/{sellerId}")
    public ResponseEntity<List<Listing>> findAllListingsInCartBySeller(@PathVariable String userId, @PathVariable String sellerId) {

        // Get the cart
        Optional<Cart> cartOpt = cartService.getCart(userId);

        if (cartOpt.isPresent()) {
            // Get all listings in the cart for the specified seller
            List<Listing> listings = listingtoCartRepository.findAllListingsInCartBySeller(cartOpt.get(), sellerId);

            if (!listings.isEmpty()) {
                return ResponseEntity.ok(listings);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            // If the cart does not exist, return not found
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/cart/get/{userId}")
    public ResponseEntity<Cart> seeCart(@PathVariable String userId) {
        // Convert the cartId to a UUID

        // Get the cart
        Optional<Cart> cartOpt = cartService.getCart(userId);

        if (cartOpt.isEmpty()) {
            cartOpt = Optional.ofNullable(cartService.createCart(userId));

        }

        // If the cart exists, return it
        return cartOpt.map(ResponseEntity::ok).orElse(null);
    }


    @PostMapping("/cart/add/{userId}/{listingId}")
    public ResponseEntity<String> addListingToCart(@PathVariable String userId, @PathVariable String listingId) {


        // Add the listing to the cart
        ListingtoCart newListingtoCart = cartService.addToCart(userId, listingId);

        if (newListingtoCart != null) {
            // If the operation is successful, return a success message
            return ResponseEntity.ok("Listing added to cart successfully.");
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/cart/remove/{userId}/{listingId}")
    public ResponseEntity<String> removeListingFromCart(@PathVariable String userId, @PathVariable String listingId) {

        Listing removedListing = cartService.removeFromCart(userId, listingId);

        if (removedListing != null) {
            // If the operation is successful, return a success message
            return ResponseEntity.ok("Listing removed from cart successfully.");
        }
        return ResponseEntity.notFound().build();

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
