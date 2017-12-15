package application.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Comment")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    @NotNull
    String commentContent;

    @NotNull
    double rateInComment;

    @ManyToOne(cascade = CascadeType.MERGE)
    public UserAccount userAcc;

    @ManyToOne(cascade = CascadeType.MERGE)
    public Event event;

    @ManyToOne(cascade = CascadeType.MERGE)
    public Hotel hotel;

    @ManyToOne(cascade = CascadeType.MERGE)
    public Restaurant restaurant;

    @ManyToOne(cascade = CascadeType.MERGE)
    public Attraction attraction;

    public Comment() {
    }

    public Comment(long id, String commentContent, double rateInComment, UserAccount userAcc, Event event, Hotel hotel, Restaurant restaurant, Attraction attraction) {
        this.id = id;
        this.commentContent = commentContent;
        this.rateInComment = rateInComment;
        this.userAcc = userAcc;
        this.event = event;
        this.hotel = hotel;
        this.restaurant = restaurant;
        this.attraction = attraction;
    }

    public long getId() {
        return id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public double getRateInComment() {
        return rateInComment;
    }

    public UserAccount getUserAcc() {
        return userAcc;
    }

    public Event getEvent() {
        return event;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setRateInComment(double rateInComment) {
        this.rateInComment = rateInComment;
    }

    public void setUserAcc(UserAccount userAcc) {
        this.userAcc = userAcc;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}
