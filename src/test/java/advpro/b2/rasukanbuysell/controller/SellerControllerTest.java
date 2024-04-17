import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SellerControllerTest {

    private SellerController sellerController;

    @BeforeEach
    void setUp() {
        sellerController = new SellerController();
    }

    @Test
    void testCreateListing() {
        ListingRequest request = new ListingRequest("New Product", "Description", 100.0, 10);
        ResponseEntity<ListingResponse> response = sellerController.createListing(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk membuat listing", response.getBody().getBody());
    }

    @Test
    void testGetAllListings() {
        String userId = "123";
        ResponseEntity<List<ListingResponse>> response = sellerController.getAllListings(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk melihat semua listing yang dibuat user " + userId, response.getBody().getBody());
    }

    @Test
    void testGetListingById() {
        String listingId = "abc123";
        ResponseEntity<ListingResponse> response = sellerController.getListingById(listingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk melihat detail listing", response.getBody().getBody());
    }

    @Test
    void testUpdateListing() {
        String listingId = "abc123";
        ListingUpdateRequest request = new ListingUpdateRequest("Updated Product", "Updated Description", 200.0, 20);
        ResponseEntity<ListingResponse> response = sellerController.updateListing(listingId, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk mengubah listing ber id-" + listingId, response.getBody().getBody());
    }

    @Test
    void testDeleteListing() {
        String listingId = "abc123";
        ResponseEntity<String> response = sellerController.deleteListing(listingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk menghapus listing ber id-" + listingId, response.getBody());
    }

    @Test
    void testGetAllOrders() {
        String userId = "123";
        ResponseEntity<List<OrderResponse>> response = sellerController.getAllOrders(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk melihat order dari user ber id-" + userId, response.getBody().getBody());
    }

    @Test
    void testUpdateOrderStatus() {
        String orderId = "xyz789";
        OrderStatusRequest request = new OrderStatusRequest("Updated Status");
        ResponseEntity<OrderResponse> response = sellerController.updateOrderStatus(orderId, request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk mengubah order ber id-" + orderId, response.getBody().getBody());
    }
}
