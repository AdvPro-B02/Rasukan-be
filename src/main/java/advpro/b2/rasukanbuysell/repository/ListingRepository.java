package advpro.b2.rasukanbuysell.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;

@Repository
public class ListingRepository {

    static int id = 0;

    private static List<Listing> listings = new ArrayList<>();

    public Listing create(Listing listing) {
        if (listing.getListingId() == null) {
            UUID uuid = UUID.randomUUID();
            listing.setListingId(uuid.toString());
        }
        
        if (isDuplicateListing(listing)) {
            return null;
        }
        
        listings.add(listing);
        return listing;
    }
    
    private boolean isDuplicateListing(Listing listing) {
        for (Listing existingListing : listings) {
            if (existingListing.getListingId().equals(listing.getListingId())) {
                return true;
            }
        }
        return false;
    }


    public Iterator<Listing> findAll() {
        return listings.iterator();
    }

    public Listing findById(String id) {
        for (Listing listing : listings) {
            if (listing.getListingId().equals(id)) {
                return listing;
            }
        }
        return null;
    }

    public static Listing updateListing(String listingId, Listing updatedListing) {
        for (int i = 0; i < listings.size(); i++) {
            Listing listing = listings.get(i);
            if (listing.getListingId().equals(listingId)) {
                listing.setName(updatedListing.getName());
                listing.setPrice(updatedListing.getPrice());
                return listing;
            }
        }
        return null;
    }

    public static void deleteListing(String listingId) {
        listings.removeIf(listing -> listing.getListingId().equals(listingId));
    }


    public User findSeller(String userId, String listingId) {
        Listing listing = findById(listingId);
        if (listing != null) {
            User owner = listing.getOwner();
            if (owner.getUserId().equals(userId)) {
                return owner;
            }
        }
        return null;
    }


}
