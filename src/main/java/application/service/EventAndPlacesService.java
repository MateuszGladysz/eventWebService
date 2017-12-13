package application.service;

import application.model.Event;
import application.model.Ticket;
import application.model.UserAccount;
import application.repository.EventRepository;
import application.repository.TicketRepository;
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

    public void addEvent(Event event, MultipartFile photo) throws IOException {

        event.setEventPhoto(event.getEventName() + ".jpg");
        Event savedEvent = this.eventRepo.save(event);
        String destination = "C:\\Users\\mateuszg\\IdeaProjects\\eventswebservice\\src\\main" +
                "\\resources\\public\\images\\eventsPictures\\"
                + savedEvent.getEventName() + ".jpg";
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

        ticket.setEventId(Long.parseLong(eventId));
        ticket.setOwnerId(userAcc.getId());
        ticket.setTicketType(ticketType);

        Event event = getOneById(Long.parseLong(eventId));

        if(ticketType.equals("normal")){
            oneTicketPrice = event.getTicketNormalPrice();
        }else oneTicketPrice = event.getTicketConcessionPrice();


        System.out.println(oneTicketPrice * Long.parseLong(ticketAmount));

        ticket.setTicketPrice(oneTicketPrice * Long.parseLong(ticketAmount));

        ticketRepo.save(ticket);

        return "";
    }

}
