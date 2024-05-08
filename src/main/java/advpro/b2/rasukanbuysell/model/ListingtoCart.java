package advpro.b2.rasukanbuysell.model;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Listing_to_Cart")
@Getter
public class ListingtoCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID listingInCartId;

    @Setter
    @ManyToOne
    @JoinColumn(name="listing_id", referencedColumnName = "listingId")
    private Listing listing;

    @Setter
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;

    @Setter
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Setter
    @Column(name = "userId", nullable = false)
    private UUID user;

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
