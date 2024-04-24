package advpro.b2.rasukanbuysell.service;

import java.util.List;

import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;

public interface ListingService {
    
    public Listing createListing(Listing listing);

    public Listing getListing(String listingId);

    public Listing updateListing(String listingId, Listing listing);

    public void deleteListing(String listingId);

    public List<Listing> getAllListings();

    public User getSeller(String userId, String listingId);

    
}
