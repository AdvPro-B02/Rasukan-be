package advpro.b2.rasukanbuysell.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Cart {
    private User owner;

    private List<Listing> insideCart;
}
