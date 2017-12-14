package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long ownerId;

    private int ticketAmount = 0;

    private String ticketType;

    private double ticketPrice = 0;

    @ManyToOne(cascade = CascadeType.MERGE)
    public UserAccount userAcc;

    @ManyToOne(cascade = CascadeType.MERGE)
    public Event event;

    public Ticket() {
    }

    public Ticket(long id, int ticketAmount, String ticketType, double ticketPrice, UserAccount userAcc, Event event) {
        this.id = id;
        this.ticketAmount = ticketAmount;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.userAcc = userAcc;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public UserAccount getUserAcc() {
        return userAcc;
    }

    public Event getEvent() {
        return event;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setTicketPrice(double ticketprice) {
        this.ticketPrice = ticketprice;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public void setUserAcc(UserAccount userAcc) {
        this.userAcc = userAcc;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
