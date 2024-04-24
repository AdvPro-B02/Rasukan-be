package advpro.b2.rasukanbuysell.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.service.CartService;
import advpro.b2.rasukanbuysell.service.ListingService;
import advpro.b2.rasukanbuysell.service.UserService;

class BuyerControllerTest {
    @InjectMocks
    BuyerController buyerController;

    @Mock
    ListingService listingService;

    @Mock
    CartService cartService;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(buyerController).build();
    }

    @Test
    void testGetListing() throws Exception {
        when(listingService.getListing("123")).thenReturn(new Listing());

        mockMvc.perform(get("/Buyer/listings/get/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testInsertToCart() throws Exception {
        mockMvc.perform(post("/Buyer/cart/add/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSeller() throws Exception {
        when(userService.getUser("123")).thenReturn(new User());

        mockMvc.perform(get("/Buyer/profile/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testRemoveListing() throws Exception {
        mockMvc.perform(post("/Buyer/cart/remove/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testSeeCart() throws Exception {
        List<Listing> cartListings = new ArrayList<>();
        cartListings.add(new Listing());
        when(cartService.getCart(new User())).thenReturn((Cart) cartListings);

        mockMvc.perform(get("/Buyer/cart/see/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCheckout() throws Exception {
        mockMvc.perform(post("/Buyer/cart/checkout/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
