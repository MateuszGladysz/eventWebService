package application.service;

import application.model.*;
import application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import java.util.List;

@Service
public class EventAndPlacesService {

    @Autowired
    private HttpSession session;

    @Autowired
    EventRepository eventRepo;

    @Autowired
    TicketRepository ticketRepo;

    @Autowired
    RestaurantRepository restaurantRepo ;

    @Autowired
    HotelRepository hotelRepo ;

    @Autowired
    AttractionRepository attractionRepo;

    @Autowired
    CommentRepository commentRepo;


    public void addEvent(Event event, MultipartFile photo) throws IOException {

        event.setEventPhoto(event.getEventName() + ".jpg");
        Event savedEvent = this.eventRepo.save(event);
        String destination = "C:\\Users\\mateuszg\\IdeaProjects\\eventswebservice\\src\\main" +
                "\\resources\\public\\images\\eventsPictures\\"
                + savedEvent.getEventName() + ".jpg";
        photo.transferTo(new File(destination));
    }

    public void addRestaurant(Restaurant restaurant, MultipartFile photo) throws IOException {

        restaurant.setRestaurantPhoto(restaurant.getRestaurantName() + ".jpg");
        Restaurant savedRestaurant = this.restaurantRepo.save(restaurant);
        String destination = "C:\\Users\\mateuszg\\IdeaProjects\\eventswebservice\\src\\main" +
                "\\resources\\public\\images\\restaurantsPictures\\"
                + savedRestaurant.getId() + ".jpg";
        photo.transferTo(new File(destination));
    }

    public void addHotel(Hotel hotel, MultipartFile photo) throws IOException {

        hotel.setHotelPhoto(hotel.getHotelName() + ".jpg");
        Hotel savedHotel = this.hotelRepo.save(hotel);
        String destination = "C:\\Users\\mateuszg\\IdeaProjects\\eventswebservice\\src\\main" +
                "\\resources\\public\\images\\hotelsPictures\\"
                + savedHotel.getId() + ".jpg";
        photo.transferTo(new File(destination));
    }

    public void addAttraction(Attraction attraction, MultipartFile photo) throws IOException {

        attraction.setAttractionPhoto(attraction.getAttractionName() + ".jpg");
        Attraction savedAttraction = this.attractionRepo.save(attraction);
        String destination = "C:\\Users\\mateuszg\\IdeaProjects\\eventswebservice\\src\\main" +
                "\\resources\\public\\images\\attractionsPictures\\"
                + savedAttraction.getId() + ".jpg";
        photo.transferTo(new File(destination));
    }


    ///getting one by id

    public Event getOneEventById(long id){return eventRepo.findOneById(id);}

    public Hotel getOneHotelById(long id){return hotelRepo.findOneById(id);}

    public Restaurant getOneRestaurantById(long id){return restaurantRepo.findOneById(id);}

    public Attraction getOneAttractionById(long id){return attractionRepo.findOneById(id);}

    /// end getting one by id

    /// gettin all

    public List<Restaurant> getAllRestaurants(){return (List)this.restaurantRepo.findAll();}

    public List<Hotel> getAllHotels(){return (List)this.hotelRepo.findAll();}

    public List<Attraction> getAllAttractions(){return (List)this.attractionRepo.findAll();}

    //// end getting all

    /// getting all by some restriction

    public List<Event> getAllEventsByType(String type){


        System.out.println("typ: " +eventRepo.findAllByEventType(type));
        return (List)this.eventRepo.findAllByEventType(type);
    }

    public List<Restaurant> getAllRestaurantsByCity(String city){return (List)this.restaurantRepo.findAllByRestaurantCity(city);}

    public List<Hotel> getAllHotelsByCity(String city){return (List)this.hotelRepo.findAllByHotelCity(city);}

    public List<Attraction> getAllAttractionsByCity(String city){return (List)this.attractionRepo.findAllByAttractionCity(city);}

    ///end getting all all by some restriction

    ///operations

    public void addComment(UserAccount userAcc, Event event, Restaurant restaurant,
                           Hotel hotel, Attraction attraction, String rateInComment,
                           String commentContent){

        if(event != null){
            List<Comment> eventComments = getAllCommentByTypeAndId(event.getId(),"event");

            event.setEventRate((eventComments.size()*event.getEventRate()+Long.parseLong(rateInComment))/
                    (eventComments.size()+1));
            eventRepo.save(event);
        }
        if(hotel != null){
            List<Comment> hotelComments = getAllCommentByTypeAndId(hotel.getId(),"hotel");
            hotel.setHotelRate((hotelComments.size()*hotel.getHotelRate()+Long.parseLong(rateInComment))/
                    (hotelComments.size()+1));
            hotelRepo.save(hotel);
        }
        if(restaurant != null){
            List<Comment> restaurantComments = getAllCommentByTypeAndId(restaurant.getId(),"restaurant");
            restaurant.setRestaurantRate((restaurantComments.size()*restaurant.getRestaurantRate()+Long.parseLong(rateInComment))/
                    (restaurantComments.size()+1));
            restaurantRepo.save(restaurant);
        }
        if(attraction != null){
            List<Comment> attractionComments = getAllCommentByTypeAndId(attraction.getId(),"attraction");
            attraction.setAttractionRate((attractionComments.size()*attraction.getAttractionRate()+Long.parseLong(rateInComment))/
                    (attractionComments.size()+1));
            attractionRepo.save(attraction);
        }

        Comment comment = new Comment();
        comment.setUserAcc(userAcc);
        comment.setEvent(event);
        comment.setAttraction(attraction);
        comment.setHotel(hotel);
        comment.setRestaurant(restaurant);

        comment.setCommentContent(commentContent);
        comment.setRateInComment(Integer.parseInt(rateInComment));

        commentRepo.save(comment);
    }

    public List<Comment> getAllCommentByTypeAndId(long id, String type){

        List<Comment> comments = null;

        if(type.equals("event")){
            comments = commentRepo.findAllByEventId(id);
        }
        if(type.equals("restaurant")){
            comments = commentRepo.findAllByRestaurantId(id);
        }
        if(type.equals("hotel")){
            comments = commentRepo.findAllByHotelId(id);
        }
        if(type.equals("attraction")){
            comments = commentRepo.findAllByAttractionId(id);
        }

        return comments;
    }

    public String buyTicket(UserAccount userAcc,String eventId,
                            String ticketType, String ticketAmount){

        double oneTicketPrice;

        Ticket ticket = new Ticket();

        Event event = getOneEventById(Long.parseLong(eventId));

        if(event.getTicketAmount() == 0){
            return "noTicketsForEvent";
        }

        ticket.setEvent(event);
        ticket.setUserAcc(userAcc);
        ticket.setTicketAmount(Integer.parseInt(ticketAmount));
        ticket.setOwnerId(userAcc.getId());
        ticket.setTicketType(ticketType);

        event.setTicketAmount(event.getTicketAmount()- Integer.parseInt(ticketAmount));

        if(ticketType.equals("normal")){
            oneTicketPrice = event.getTicketNormalPrice();
        }else oneTicketPrice = event.getTicketConcessionPrice();


        ticket.setTicketPrice(oneTicketPrice * Long.parseLong(ticketAmount));

        ticketRepo.save(ticket);
        eventRepo.save(event);

        return "";
    }

}
