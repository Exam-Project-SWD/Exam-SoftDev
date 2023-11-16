import dtos.MenuDTO;
import dtos.RestaurantDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestaurantTest {

    @Test
    public void testAddToMenu() {
        // Create a restaurant DTO
        RestaurantDTO restaurantDTO = new RestaurantDTO(1, "Food Paradise", "123 Main Street", null);

        // Simulate creating a menu item DTO
        // Menu item details: itemId=1, itemName="Pizza", price=10.99
        MenuDTO menuDTO = new MenuDTO(1, "Pizza", 10.99);

        // Add menu item to the restaurant's menu
        restaurantDTO.addToMenu(menuDTO);

        // Verify that the menu item was added successfully
        assertNotNull(restaurantDTO.getMenu());
        assertEquals(1, restaurantDTO.getMenu().size());
        assertEquals(menuDTO, restaurantDTO.getMenu().get(0));
    }
}
