import dtos.CustomerDTO;
import dtos.OrderDTO;
import dtos.MenuDTO;
import dtos.RestaurantDTO;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {

    @Test
    public void testPlaceOrder() {
        // Create a customer DTO
        CustomerDTO customerDTO = new CustomerDTO(1, "John Doe", "john@example.com", "123456789", new ArrayList<>());

        // Create a restaurant DTO
        RestaurantDTO restaurantDTO = new RestaurantDTO(1, "Food Paradise", "123 Main Street", new ArrayList<>());

        // Simulate creating a menu item DTO
        // For simplicity, let's assume the MenuDTO class has already been created
        // and you have a method to add items to the restaurant's menu
        // Menu item details: itemId=1, itemName="Pizza", price=10.99
        MenuDTO menuDTO = new MenuDTO(1, "Pizza", 10.99);
        restaurantDTO.addToMenu(menuDTO);

        // Customer places an order
        OrderDTO orderDTO = new OrderDTO(1, customerDTO, restaurantDTO, LocalDateTime.now(), "In Progress");

        // Add menu item to the order
        orderDTO.addMenuItem(menuDTO);

        // Verify that the order was placed successfully
        assertNotNull(orderDTO);
        assertEquals(customerDTO, orderDTO.getCustomer());
        assertEquals(restaurantDTO, orderDTO.getRestaurant());
        assertEquals(1, orderDTO.getOrderItems().size()); // Assuming you have a method getOrderItems() in the OrderDTO class
        assertEquals("In Progress", orderDTO.getStatus());
    }
}
