package advpro.b2.rasukanbuysell.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;

class ListingRepositoryTest {
    ListingRepository listingRepository;
    
    @BeforeEach
    void setUp() {
        listingRepository = new ListingRepository();
    }

    @Test
    void testCreate() {
        Listing listing = new Listing();
        listing.setListingId("1234");
        listing.setName("Test Listing");
        listing.setPrice(100);
        User user = new User();
        listing.setOwner(user);

        Listing createdListing = listingRepository.create(listing);

        assertNotNull(createdListing);
        assertEquals("1234", createdListing.getListingId());
        assertEquals("Test Listing", createdListing.getName());
        assertEquals(100, createdListing.getPrice());
        assertEquals(user, createdListing.getOwner());
    }

    @Test
    void testCreateDuplicateId() {
        Listing listing1 = new Listing();
        listing1.setListingId("123");
        listingRepository.create(listing1);

        Listing listing2 = new Listing();
        listing2.setListingId("123");
        Listing createdListing = listingRepository.create(listing2);

        assertNull(createdListing);
    }

    @Test
    void testFindAll() {
        Listing listing = new Listing();
        listing.setListingId("123");
        listingRepository.create(listing);

        Iterator<Listing> listings = listingRepository.findAll();

        assertNotNull(listings);
        assertTrue(listings.hasNext());
        assertEquals("123", listings.next().getListingId());
    }

    @Test
    void testFindById() {
        Listing listing = new Listing();
        listing.setListingId("123");
        listingRepository.create(listing);

        Listing foundListing = listingRepository.findById("123");

        assertNotNull(foundListing);
        assertEquals("123", foundListing.getListingId());
    }

    @Test
    void testFindByIdNotFound() {
        Listing foundListing = listingRepository.findById("nonexistent");

        assertNull(foundListing);
    }

    @Test
    void testUpdateListing() {
        Listing listing = new Listing();
        listing.setListingId("123");
        listing.setName("Test Listing");
        listing.setPrice(100);
        listingRepository.create(listing);

        Listing updatedListing = new Listing();
        updatedListing.setListingId("123");
        updatedListing.setName("Updated Listing");
        updatedListing.setPrice(200);

        Listing result = ListingRepository.updateListing("123", updatedListing);

        assertNotNull(result);
        assertEquals("123", result.getListingId());
        assertEquals("Updated Listing", result.getName());
        assertEquals(200, result.getPrice());
    }

    @Test
    void testDeleteListing() {
        Listing listing = new Listing();
        listing.setListingId("123");
        listingRepository.create(listing);

        ListingRepository.deleteListing("123");

        Listing deletedListing = listingRepository.findById("123");

        assertNull(deletedListing);
    }

    @Test
    void testFindSeller() {
        User user = new User();
        user.setUserId("4566");
        user.setName("Test User");

        Listing listing = new Listing();
        listing.setListingId("12345");
        listing.setOwner(user);
        listingRepository.create(listing);

        User foundUser = listingRepository.findSeller("4566", "12345");

        assertNotNull(foundUser);
        assertEquals("4566", foundUser.getUserId());
        assertEquals("Test User", foundUser.getName());
    }


}
