package application.controller;


import application.model.UserAccount;
import application.service.EventAndPlacesService;
import application.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HttpSession session;

    @Autowired
    EventAndPlacesService eventAndPlacesServ;

    @Autowired
    TicketsService ticketsServ;

    @RequestMapping(method= RequestMethod.GET)
    public String home(){

        UserAccount userAcc = (UserAccount)session.getAttribute("loggedUser");

        if(userAcc != null && userAcc.getUserProf().getWorker() == true){
            return "workerPanel";
        }
        return "home";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/registry", method = {RequestMethod.GET})
    public String registry() {
        return "registry";
    }

    @RequestMapping(value = "/account", method = {RequestMethod.GET})
    public String account() {return "account"; }

    @RequestMapping(value = "/workerPanel", method = {RequestMethod.GET})
    public String workerPanel() {

        UserAccount userAcc = (UserAccount)session.getAttribute("loggedUser");

        if(userAcc != null && userAcc.getUserProf().getWorker() == true){
            return "workerPanel";
        }
        return "home";
    }


    @RequestMapping(value = "/restaurant/{city}", method = {RequestMethod.GET})
    public String restaurant(@PathVariable String city) {

        if(!city.equals("noCity")){
            session.setAttribute("restaurants",eventAndPlacesServ.getAllRestaurantsByCity(city));
        }else{
            session.setAttribute("restaurants",eventAndPlacesServ.getAllRestaurants());
        }

        return "restaurant";
    }


    @RequestMapping(value = "/hotel/{city}", method = {RequestMethod.GET})
    public String hotel(@PathVariable String city) {

        if(!city.equals("noCity")){
            session.setAttribute("hotels",eventAndPlacesServ.getAllHotelsByCity(city));
        }else{
            session.setAttribute("hotels",eventAndPlacesServ.getAllHotels());
        }

        return "hotel";
    }

    @RequestMapping(value = "/attraction/{city}", method = {RequestMethod.GET})
    public String attraction(@PathVariable String city) {

        if(!city.equals("noCity")){
            session.setAttribute("attractions",eventAndPlacesServ.getAllAttractionsByCity(city));
        }else{
            session.setAttribute("attractions",eventAndPlacesServ.getAllAttractions());
        }
        return "attraction";
    }

    @RequestMapping(value="/event/{type}", method = RequestMethod.GET)
    public String event(@PathVariable String type){

        session.setAttribute("events",eventAndPlacesServ.getAllEventsByType(type));

        return "event";
    }

    @RequestMapping(value = "/myTickets", method = {RequestMethod.GET})
    public String myTickets() {

        UserAccount userAcc = (UserAccount) session.getAttribute("loggedUser");
        if(userAcc == null){
            return "home";
        }
        session.setAttribute("userTickets",ticketsServ.getTicketByOwnerId(userAcc.getId()));
        return "myTickets";

    }




}
