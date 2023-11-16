package dtos;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDTO {
    private int restaurantId;
    private String name;
    private String location;
    private List<MenuDTO> menu;

    public RestaurantDTO(int restaurantId, String name, String location, List<MenuDTO> menu) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.menu = menu != null ? new ArrayList<>(menu) : new ArrayList<>();
    }

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

    public List<MenuDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuDTO> menu) {
        this.menu = menu != null ? new ArrayList<>(menu) : new ArrayList<>();
    }

    // Other methods related to restaurant functionality (menu management, etc.)
    public void addToMenu(MenuDTO item) {
        this.menu.add(item);
    }

    public void removeFromMenu(MenuDTO item) {
        this.menu.remove(item);
    }

    // Other methods as needed

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RestaurantDTO that = (RestaurantDTO) obj;
        return restaurantId == that.restaurantId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(restaurantId);
    }
}
