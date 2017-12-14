package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String hotelName;

    @NotNull
    private String hotelDescription;

    @NotNull
    private String hotelAddress;

    @NotNull
    private String hotelCity;


    private double hotelMinRoomForOnePrice;

    private double hotelMinRoomForTwoPrice;

    private double hotelMinRoomForMorePrice;

    private double hotelRate;

    private String hotelPhoto;

    public Hotel() {
    }

    public Hotel(long id, String hotelName, String hotelDescription, String hotelAddress, String hotelCity, double hotelMinRoomForOnePrice, double hotelMinRoomForTwoPrice, double hotelMinRoomForMorePrice, double hotelRate, String hotelPhoto) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.hotelAddress = hotelAddress;
        this.hotelCity = hotelCity;
        this.hotelMinRoomForOnePrice = hotelMinRoomForOnePrice;
        this.hotelMinRoomForTwoPrice = hotelMinRoomForTwoPrice;
        this.hotelMinRoomForMorePrice = hotelMinRoomForMorePrice;
        this.hotelRate = hotelRate;
        this.hotelPhoto = hotelPhoto;
    }


    public long getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public double getHotelMinRoomForOnePrice() {
        return hotelMinRoomForOnePrice;
    }

    public double getHotelMinRoomForTwoPrice() {
        return hotelMinRoomForTwoPrice;
    }

    public double getHotelMinRoomForMorePrice() {
        return hotelMinRoomForMorePrice;
    }

    public double getHotelRate() {
        return hotelRate;
    }

    public String getHotelPhoto() {
        return hotelPhoto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public void setHotelMinRoomForOnePrice(double hotelMinRoomForOnePrice) {
        this.hotelMinRoomForOnePrice = hotelMinRoomForOnePrice;
    }

    public void setHotelMinRoomForTwoPrice(double hotelMinRoomForTwoPrice) {
        this.hotelMinRoomForTwoPrice = hotelMinRoomForTwoPrice;
    }

    public void setHotelMinRoomForMorePrice(double hotelMinRoomForMorePrice) {
        this.hotelMinRoomForMorePrice = hotelMinRoomForMorePrice;
    }

    public void setHotelRate(double hotelRate) {
        this.hotelRate = hotelRate;
    }

    public void setHotelPhoto(String hotelPhoto) {
        this.hotelPhoto = hotelPhoto;
    }
}
