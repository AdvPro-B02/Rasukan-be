package advpro.b2.rasukanbuysell.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Listing {
    private String listingId;
    private String name;
    private int price;
    private User owner;
    private Cart cart;
}

