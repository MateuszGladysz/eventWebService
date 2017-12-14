package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Attraction")
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String attractionName;

    @NotNull
    private String attractionDescription;

    @NotNull
    private String attractionAddress;

    @NotNull
    private String attractionCity;

    private double attractionRate;

    private String attractionPriceList;

    private String attractionPhoto;


    public Attraction() {
    }

    public Attraction(long id, String attractionName, String attractionDescription, String attractionAddress, String attractionCity, double attractionRate, String attractionPriceList, String attractionPhoto) {
        this.id = id;
        this.attractionName = attractionName;
        this.attractionDescription = attractionDescription;
        this.attractionAddress = attractionAddress;
        this.attractionCity = attractionCity;
        this.attractionRate = attractionRate;
        this.attractionPriceList = attractionPriceList;
        this.attractionPhoto = attractionPhoto;
    }

    public long getId() {
        return id;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public String getAttractionDescription() {
        return attractionDescription;
    }

    public String getAttractionAddress() {
        return attractionAddress;
    }

    public String getAttractionCity() {
        return attractionCity;
    }

    public double getAttractionRate() {
        return attractionRate;
    }

    public String getAttractionPriceList() {
        return attractionPriceList;
    }

    public String getAttractionPhoto() {
        return attractionPhoto;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public void setAttractionDescription(String attractionDescription) {
        this.attractionDescription = attractionDescription;
    }

    public void setAttractionAddress(String attractionAddress) {
        this.attractionAddress = attractionAddress;
    }

    public void setAttractionCity(String attractionCity) {
        this.attractionCity = attractionCity;
    }

    public void setAttractionRate(double attractionRate) {
        this.attractionRate = attractionRate;
    }

    public void setAttractionPriceList(String attractionPriceList) {
        this.attractionPriceList = attractionPriceList;
    }

    public void setAttractionPhoto(String attractionPhoto) {
        this.attractionPhoto = attractionPhoto;
    }
}
