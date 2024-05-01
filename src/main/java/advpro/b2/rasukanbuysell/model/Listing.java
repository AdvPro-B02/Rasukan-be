package advpro.b2.rasukanbuysell.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="listing")
@Getter
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String listingId;

    @Setter
    @Column(name="listingName", nullable = false)
    private String name;

    @Setter
    @Column(name="listingPrice", nullable = false)
    private int price;

    @Setter
    @Column(name="stock", nullable = false)
    private int stock;

    public Listing(String name, int stock, int price){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Listing() {

    }

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

