package application.controller;


import application.model.UserAccount;
import application.service.EventAndPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HttpSession session;

    @Autowired
    EventAndPlacesService eventAndPlacesServ;

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
    public String workerPanel() {return "workerPanel"; }

    @RequestMapping(value="/event/{type}", method = RequestMethod.GET)
    public String event(@PathVariable String type){

        session.setAttribute("events",eventAndPlacesServ.getAllEventsByType(type));

        return "event";
    }




}
