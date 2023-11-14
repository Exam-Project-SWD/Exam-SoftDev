import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RestaurantTest {

    @Test
    public void testAddToMenu() {
        // Create a sample restaurant
        Restaurant restaurant = new Restaurant(1, "Sample Restaurant", "123 Main St");

        // Create a sample menu item
        Menu menu1 = new Menu(1, "Burger", 10.99);

        // Add the menu item to the restaurant's menu
        restaurant.addToMenu(menu1);

        // Assert that the menu item has been added to the menu
        assertTrue(restaurant.getMenu().contains(menu1));
    }

    @Test
    public void testRemoveFromMenu() {
        // Create a sample restaurant
        Restaurant restaurant = new Restaurant(1, "Sample Restaurant", "123 Main St");

        // Create a sample menu item
        Menu menu1 = new Menu(1, "Burger", 10.99);

        // Add the menu item to the restaurant's menu
        restaurant.addToMenu(menu1);

        // Remove the menu item from the restaurant's menu
        restaurant.removeFromMenu(menu1);

        // Assert that the menu item has been removed from the menu
        assertFalse(restaurant.getMenu().contains(menu1));
    }
}
