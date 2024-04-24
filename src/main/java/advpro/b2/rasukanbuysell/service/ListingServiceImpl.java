package advpro.b2.rasukanbuysell.service;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.repository.ListingRepository;

@Service
public class ListingServiceImpl implements ListingService{

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Listing createListing(Listing listing){
        listingRepository.create(listing);
        return listing;
    }

    @Override
    public Listing getListing(String listingId) {
        Listing listing = listingRepository.findById(listingId);
        return listing;
    }

    @Override
    public Listing updateListing(String listingId, Listing listing) {
        ListingRepository.updateListing(listingId, listing);
        return listing;
    }

    @Override
    public void deleteListing(String listingId) {
        ListingRepository.deleteListing(listingId);
    }

    @Override
    public List<Listing> getAllListings() {
        Iterator<Listing> listingIterator = listingRepository.findAll();
        List<Listing> allListings = new ArrayList<>();
        listingIterator.forEachRemaining(allListings::add);
        return allListings;
    }

    @Override
    public User getSeller(String userId, String listingId) {
        User seller = listingRepository.findSeller(userId, listingId);
        return seller;
    }
}
