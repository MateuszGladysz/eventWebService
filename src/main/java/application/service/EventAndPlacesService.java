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

    public List<Event> getAllEventsByType(String type){


        System.out.println("typ: " +eventRepo.findAllByEventType(type));
        return (List)this.eventRepo.findAllByEventType(type);
    }


    public Event getOneById(long id){return eventRepo.findOneById(id);}

    public String buyTicket(UserAccount userAcc,String eventId,
                            String ticketType, String ticketAmount){

        double oneTicketPrice;

        Ticket ticket = new Ticket();

        Event event = getOneById(Long.parseLong(eventId));

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
