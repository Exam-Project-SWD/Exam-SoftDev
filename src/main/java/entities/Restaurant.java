package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;

    private String name;
    private String location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menu;

    // Constructors, getters, and setters...

    // Getters and setters for restaurant attributes
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    // Other methods related to restaurant functionality (menu management, etc.)
    public void addToMenu(Menu item) {
        this.menu.add(item);
        // Logic to add an item to the restaurant's menu
    }

    public void removeFromMenu(Menu item) {
        this.menu.remove(item);
        // Logic to remove an item from the restaurant's menu
    }
}
