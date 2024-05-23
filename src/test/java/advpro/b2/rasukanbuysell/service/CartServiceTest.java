//package advpro.b2.rasukanbuysell.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import advpro.b2.rasukanbuysell.model.Cart;
//import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.User;
//import advpro.b2.rasukanbuysell.repository.CartRepository;
//
//class CartServiceImplTest {
//    @InjectMocks
//    CartServiceImpl cartService;
//
//    @Mock
//    CartRepository cartRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testCreateCart() {
//        User user = new User();
//        user.setUserId("123");
//        user.setName("Test User");
//
//        Cart cart = new Cart(user);
//
//        when(cartRepository.create(user)).thenReturn(cart);
//
//        Cart createdCart = cartService.createCart(user);
//
//        assertNotNull(createdCart);
//        assertEquals(user, createdCart.getOwner());
//    }
//
//    @Test
//    void testGetCart() {
//        User user = new User();
//        user.setUserId("123");
//        user.setName("Test User");
//
//        Cart cart = new Cart(user);
//
//        when(cartRepository.findByUser(user)).thenReturn(cart);
//
//        Cart foundCart = cartService.getCart(user);
//
//        assertNotNull(foundCart);
//        assertEquals(user, foundCart.getOwner());
//    }
//
//    @Test
//    void testAddToCart() {
//        User user = new User();
//        user.setUserId("123");
//        user.setName("Test User");
//
//        Cart cart = new Cart(user);
//
//        List<Listing> insideCart = new ArrayList<>();
//        Listing listing = new Listing();
//        listing.setListingId("456");
//        listing.setName("Test Listing");
//        listing.setPrice(100);
//        insideCart.add(listing);
//        cart.setInsideCart(insideCart);
//
//        when(cartRepository.findByUser(user)).thenReturn(cart);
//        when(cartRepository.updateCart(user, insideCart)).thenReturn(cart);
//
//        cartService.addToCart(user, listing);
//
//        Cart updatedCart = cartService.getCart(user);
//
//        assertNotNull(updatedCart);
//        // TODO: masih perlu benerin
////        assertEquals(1, updatedCart.getInsideCart().size());
////        assertEquals(listing, updatedCart.getInsideCart().get(0));
//    }
//
//    @Test
//    void testUpdateCart() {
//        User user = new User();
//        user.setUserId("123");
//        user.setName("Test User");
//
//        Cart cart = new Cart(user);
//
//        List<Listing> updatedInsideCart = new ArrayList<>();
//        Listing listing = new Listing();
//        listing.setListingId("456");
//        listing.setName("Test Listing");
//        listing.setPrice(100);
//        updatedInsideCart.add(listing);
//
//        when(cartRepository.updateCart(user, updatedInsideCart)).thenReturn(cart);
//
//        Cart updatedCart = cartService.updateCart(user, updatedInsideCart);
//
//        assertNotNull(updatedCart);
//        // TODO: benerin
////        assertEquals(updatedInsideCart, updatedCart.getInsideCart());
//    }
//
//    @Test
//    void testRemoveFromCart() {
//        User user = new User();
//        user.setUserId("123");
//        user.setName("Test User");
//
//        Cart cart = new Cart(user);
//
//        List<Listing> insideCart = new ArrayList<>();
//        Listing listing = new Listing();
//        listing.setListingId("456");
//        listing.setName("Test Listing");
//        listing.setPrice(100);
//        insideCart.add(listing);
//        cart.setInsideCart(insideCart);
//
//        when(cartRepository.findByUser(user)).thenReturn(cart);
//        when(cartRepository.updateCart(user, insideCart)).thenReturn(cart);
//
//        Cart updatedCart = cartService.removeFromCart(user, listing);
//
//        assertNotNull(updatedCart);
//        assertEquals(0, updatedCart.getInsideCart().size());
//    }
//
//    // TODO: checkout masih mager implement, banyak dependency
//    @Test
//    void testCheckout() {
//        User user = new User();
//        user.setUserId("123");
//        user.setName("Test User");
//
//        Cart cart = new Cart(user);
//
//        List<Listing> insideCart = new ArrayList<>();
//        Listing listing = new Listing();
//        listing.setListingId("456");
//        listing.setName("Test Listing");
//        listing.setPrice(100);
//        insideCart.add(listing);
//        cart.setInsideCart(insideCart);
//
//        when(cartRepository.findByUser(user)).thenReturn(cart);
//        when(cartRepository.updateCart(user, new ArrayList<>())).thenReturn(cart);
//
//        cartService.checkout(user);
//
//        Cart updatedCart = cartService.getCart(user);
//
//        assertNotNull(updatedCart);
//        assertEquals(0, updatedCart.getInsideCart().size());
//    }
//
//
//}
