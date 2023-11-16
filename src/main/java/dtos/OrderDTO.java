package dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private int orderId;
    private CustomerDTO customer;
    private RestaurantDTO restaurant;
    private LocalDateTime orderTime;
    private String status;
    private List<MenuDTO> orderItems;

    public OrderDTO(int orderId, CustomerDTO customer, RestaurantDTO restaurant,
                    LocalDateTime orderTime, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderTime = orderTime;
        this.status = status;
        this.orderItems = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MenuDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<MenuDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public void addMenuItem(MenuDTO item) {
        this.orderItems.add(item);
    }

    // Other methods related to order functionalities
    // (e.g., methods to update order status, calculate order value, etc.)
}

