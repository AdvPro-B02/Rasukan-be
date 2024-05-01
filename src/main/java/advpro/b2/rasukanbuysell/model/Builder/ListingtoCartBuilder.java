package advpro.b2.rasukanbuysell.model.Builder;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.ListingtoCart;
import lombok.Setter;

@Setter
public class ListingtoCartBuilder {
    ListingtoCart listingToCart;
    public ListingtoCartBuilder(){
        listingToCart = new ListingtoCart();
    }
    public ListingtoCartBuilder setQuantity(int quantity){
        listingToCart.setQuantity(quantity);
        return this;
    }

    public ListingtoCartBuilder setListing(Listing listing){
        listingToCart.setListing(listing);
        return this;
    }

    public ListingtoCartBuilder setCart(Cart cart){
        listingToCart.setCart(cart);
        return this;
    }

    public ListingtoCart build(){
        return listingToCart;
    }
}

