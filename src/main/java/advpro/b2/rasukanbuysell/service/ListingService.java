package advpro.b2.rasukanbuysell.service;

import java.util.List;
import java.util.Optional;

import advpro.b2.rasukanbuysell.model.Listing;
import org.springframework.web.bind.annotation.RequestHeader;
//import advpro.b2.rasukanbuysell.model.User;

public interface ListingService {
    
    public Listing createListing(Listing listing);

    public Optional<Listing> getListing(String listingId);

    public Listing updateListing(Listing listing);

    public void deleteListing(String listingId);

    public List<Listing> getAllListings();

    public Listing buildListing(Listing listing);

//    public User getSeller(String userId, String listingId);

    
}
