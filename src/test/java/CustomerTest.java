import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.assertTrue;


public class CustomerTest {

    @Test
    public void testPlaceOrder() {
        // Create a sample customer
        Customer customer = new Customer(1, "John Doe", "john@example.com", "123-456-7890");

        // Create a sample restaurant
        Restaurant restaurant = new Restaurant(1, "Sample Restaurant", "123 Main St");

        // Create a sample menu item
        Menu menu1 = new Menu(1, "Burger", 10.99);
        restaurant.addToMenu(menu1);

        // Assume the customer selects the menu item
        Order order = new Order(1, customer, restaurant, LocalDateTime.now());

        // Place the order
        customer.placeOrder(order);

        // Assert that the customer's order list contains the placed order
        assertTrue(customer.getOrders().contains(order));
    }
}
