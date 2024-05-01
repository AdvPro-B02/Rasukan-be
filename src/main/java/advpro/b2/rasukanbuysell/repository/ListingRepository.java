package advpro.b2.rasukanbuysell.repository;

import org.springframework.stereotype.Repository;
import advpro.b2.rasukanbuysell.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

}