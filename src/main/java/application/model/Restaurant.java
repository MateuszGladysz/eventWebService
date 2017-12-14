package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String restaurantName;

    @NotNull
    private String restaurantDescription;

    @NotNull
    private String restaurantAddress;

    @NotNull
    private String restaurantCity;

    private double restaurantRate;

    private String restaurantPhoto;

    public Restaurant() {
    }

    public Restaurant(long id, String restaurantName, String restaurantDescription, String restaurantAddress, String restaurantCity, double restaurantRate, String restaurantPhoto) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantAddress = restaurantAddress;
        this.restaurantCity = restaurantCity;
        this.restaurantRate = restaurantRate;
        this.restaurantPhoto = restaurantPhoto;
    }

    public long getId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public double getRestaurantRate() {
        return restaurantRate;
    }

    public String getRestaurantPhoto() {
        return restaurantPhoto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public void setRestaurantRate(double restaurantRate) {
        this.restaurantRate = restaurantRate;
    }

    public void setRestaurantPhoto(String restaurantPhoto) {
        this.restaurantPhoto = restaurantPhoto;
    }
}
