package advpro.b2.rasukanbuysell.repository;

import advpro.b2.rasukanbuysell.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("SELECT c FROM Cart c WHERE c.ownerId = :ownerId")
    Optional<Cart> getOwner(@Param("ownerId") UUID ownerId);
}
