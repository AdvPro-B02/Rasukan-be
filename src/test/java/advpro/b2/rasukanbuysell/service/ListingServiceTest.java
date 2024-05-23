//package advpro.b2.rasukanbuysell.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.User;
//import advpro.b2.rasukanbuysell.repository.ListingRepository;
//
//class ListingServiceTest {
//    @InjectMocks
//    ListingServiceImpl listingService;
//
//    @Mock
//    ListingRepository listingRepository;
//
//    @SuppressWarnings("deprecation")
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testCreateListing() {
//        Listing listing = new Listing();
//        listing.setListingId("123");
//        listing.setName("Test Listing");
//        listing.setPrice(100);
//        User user = new User();
//        listing.setOwner(user);
//
//        Mockito.when(listingRepository.create(listing)).thenReturn(listing);
//
//        Listing createdListing = listingService.createListing(listing);
//
//        assertNotNull(createdListing);
//        assertEquals("123", createdListing.getListingId());
//        assertEquals("Test Listing", createdListing.getName());
//        assertEquals(100, createdListing.getPrice());
//        assertEquals(user, createdListing.getOwner());
//    }
//
////    @Test
////    void testGetListing() {
////        Listing listing = new Listing();
////        listing.setListingId("123");
////
////        Mockito.when(listingRepository.findById("123")).thenReturn(listing);
////
////        Listing foundListing = listingService.getListing("123");
////
////        assertNotNull(foundListing);
////        assertEquals("123", foundListing.getListingId());
////    }
//
//    @Test
//    void testUpdateListing() {
//        Listing listing = new Listing();
//        listing.setListingId("123");
//        listing.setName("Test Listing");
//        listing.setPrice(100);
//
//        Listing updatedListing = new Listing();
//        updatedListing.setListingId("123");
//        updatedListing.setName("Updated Listing");
//        updatedListing.setPrice(200);
//
//        Mockito.when(listingRepository.updateListing("123", updatedListing)).thenReturn(updatedListing);
//
//        Listing result = listingService.updateListing("123", updatedListing
//        );
//
//        assertNotNull(result);
//        assertEquals("123", result.getListingId());
//        assertEquals("Updated Listing", result.getName());
//        assertEquals(200, result.getPrice());
//    }
//
//    //TODO: benerin ini
////    @Test
////    void testDeleteListing() {
////        Listing listing = new Listing();
////        listing.setListingId("12333");
////
////        Mockito.doNothing().when(listingRepository).deleteListing("12333");
////
////        listingRepository.deleteListing("12333");
////
////        listingService.deleteListing("12333");
////
////        Mockito.verify(listingRepository, Mockito.times(1)).deleteListing("12333");
////    }
//
//    @Test
//    void testGetAllListings() {
//        Listing listing1 = new Listing();
//        listing1.setListingId("123");
//        Listing listing2 = new Listing();
//        listing2.setListingId("456");
//
//        List<Listing> listings = new ArrayList<>();
//        listings.add(listing1);
//        listings.add(listing2);
//
//        Mockito.when(listingRepository.findAll()).thenReturn(listings.iterator());
//
//        List<Listing> allListings = listingService.getAllListings();
//
//        assertNotNull(allListings);
//        assertEquals(2, allListings.size());
//        assertEquals("123", allListings.get(0).getListingId());
//        assertEquals("456", allListings.get(1).getListingId());
//    }
//
//    @Test
//    void testGetSeller() {
//        User user = new User();
//        user.setUserId("456");
//        user.setName("Test User");
//
//        Listing listing = new Listing();
//        listing.setListingId("123");
//        listing.setOwner(user);
//
//        Mockito.when(listingRepository.findSeller("456", "123")).thenReturn(user);
//
//        User foundUser = listingService.getSeller("456", "123");
//
//        assertNotNull(foundUser);
//        assertEquals("456", foundUser.getUserId());
//        assertEquals("Test User", foundUser.getName());
//    }
//}
