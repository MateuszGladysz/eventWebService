package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String eventName;

    @NotNull
    private String eventDescription;

    @NotNull
    private String eventDate;

    @NotNull
    private int ticketAmount;

    @NotNull
    private String eventAddress;

    @NotNull
    private String eventCity;

    @NotNull
    private String eventType;

    private double ticketNormalPrice;

    private double ticketConcessionPrice;

    private double eventRate;

    private String eventPhoto;

    public Event() {
    }

    public Event(long id, String eventName, String eventDescription, String eventDate, int ticketAmount, String eventAddress, String eventCity, String eventType, double ticketNormalPrice, double ticketConcessionPrice, double eventRate, String eventPhoto) {
        this.id = id;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.ticketAmount = ticketAmount;
        this.eventAddress = eventAddress;
        this.eventCity = eventCity;
        this.eventType = eventType;
        this.ticketNormalPrice = ticketNormalPrice;
        this.ticketConcessionPrice = ticketConcessionPrice;
        this.eventRate = eventRate;
        this.eventPhoto = eventPhoto;
    }

    public long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventDate() {
        return eventDate;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public String getEventCity() {
        return eventCity;
    }

    public double getTicketNormalPrice() {
        return ticketNormalPrice;
    }

    public double getTicketConcessionPrice() {
        return ticketConcessionPrice;
    }

    public String getEventPhoto() {
        return eventPhoto;
    }

    public String getEventType() {
        return eventType;
    }

    public double getEventRate() {
        return eventRate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public void setEventAddress(String eventAdress) {
        this.eventAddress = eventAdress;
    }

    public void setEventCity(String eventCity) {
        this.eventCity = eventCity;
    }

    public void setTicketNormalPrice(double ticketNormalPrice) {
        this.ticketNormalPrice = ticketNormalPrice;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setTicketConcessionPrice(double ticketConcessionPrice) {
        this.ticketConcessionPrice = ticketConcessionPrice;
    }

    public void setEventPhoto(String eventPhoto) {
        this.eventPhoto = eventPhoto;
    }

    public void setEventRate(double eventRate) {
        this.eventRate = eventRate;
    }
}
