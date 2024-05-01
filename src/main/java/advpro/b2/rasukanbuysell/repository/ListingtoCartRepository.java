package advpro.b2.rasukanbuysell.repository;

import advpro.b2.rasukanbuysell.model.ListingtoCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListingtoCartRepository extends JpaRepository<ListingtoCart, UUID> {
}
