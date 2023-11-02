import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private Customer customer;
    private Restaurant restaurant;
    private LocalDateTime orderTime;
    private String status; // Pending, In Progress, Delivered, etc.
    // Other relevant order details

    public Order(int orderId, Customer customer, Restaurant restaurant, LocalDateTime orderTime) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderTime = orderTime;
        this.status = "Pending"; // Default status
    }

    // Getters and setters for order attributes
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
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

    // Other methods related to order functionalities
    // (e.g., methods to update order status, calculate order value, etc.)
}

