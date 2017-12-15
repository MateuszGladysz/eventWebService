package application.controller;

import application.model.Event;
import application.model.UserAccount;
import application.service.EventAndPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/event")
public class EventController {


    @Autowired
    EventAndPlacesService eventAndPlacesServ;

    @Autowired
    private HttpSession session;


    @RequestMapping(value="/getDetails/{id}", method = RequestMethod.GET)
    public String getEventDetails(@PathVariable String id){

        session.setAttribute("eventDetails",eventAndPlacesServ.getOneEventById(Long.parseLong(id)));


        return "eventDetails";
    }


    @RequestMapping(value="/buyTicket", method = RequestMethod.POST)
    public String buyTicket(@RequestParam String eventId, @RequestParam String ticketType,
                            @RequestParam String ticketAmount, Map<String,Object> model,
                            HttpServletRequest request){

        if(session.getAttribute("loggedUser") == null) {

            session.setAttribute("buyTicketFailureMessage", "Musisz być zalogowany aby kupić bilet");
            session.setAttribute("previousPageUrl",request.getHeader("Referer"));
            return "redirect:/login";

        }


        eventAndPlacesServ.buyTicket((UserAccount) session.getAttribute("loggedUser"),
                eventId, ticketType, ticketAmount);

        return "eventDetails";
    }

    @RequestMapping(value="/addComment", method = RequestMethod.POST)
    public String addComment(@RequestParam String commentEventId, @RequestParam String commentContent,
                             @RequestParam String rateInComment){

        Event event = (Event)eventAndPlacesServ.getOneEventById(Long.parseLong(commentEventId));
        UserAccount userAcc = (UserAccount) session.getAttribute("loggedUser");
        eventAndPlacesServ.addComment(userAcc,event,null,null,null,
                rateInComment,commentContent);


        return "eventDetails";
    }



}
