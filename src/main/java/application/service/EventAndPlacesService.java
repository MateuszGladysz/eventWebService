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

    ///end getting all all by some restriction

    ///operations

    public void addComment(UserAccount userAcc, Event event, Restaurant restaurant,
                           Hotel hotel, Attraction attraction, String rateInComment,
                           String commentContent){

        Comment comment = new Comment();
        comment.setEvent(event);
        comment.setAttraction(attraction);
        comment.setHotel(hotel);
        comment.setRestaurant(restaurant);

        comment.setCommentContent(commentContent);
        comment.setRateInComment(Double.parseDouble(rateInComment));

        commentRepo.save(comment);
    }

    public String buyTicket(UserAccount userAcc,String eventId,
                            String ticketType, String ticketAmount){

        double oneTicketPrice;

        Ticket ticket = new Ticket();

        Event event = getOneEventById(Long.parseLong(eventId));

        ticket.setEvent(event);
        ticket.setUserAcc(userAcc);
        ticket.setTicketAmount(Integer.parseInt(ticketAmount));
        ticket.setOwnerId(userAcc.getId());
        ticket.setTicketType(ticketType);


        if(ticketType.equals("normal")){
            oneTicketPrice = event.getTicketNormalPrice();
        }else oneTicketPrice = event.getTicketConcessionPrice();


        System.out.println(oneTicketPrice * Long.parseLong(ticketAmount));

        ticket.setTicketPrice(oneTicketPrice * Long.parseLong(ticketAmount));

        ticketRepo.save(ticket);

        return "";
    }

}
