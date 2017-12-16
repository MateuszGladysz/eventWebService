package application.controller;




import application.model.Attraction;
import application.model.Hotel;
import application.model.Restaurant;
import application.model.UserAccount;
import application.service.EventAndPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private HttpSession session;

    @Autowired
    EventAndPlacesService eventAndPlacesServ;


    @RequestMapping(value="/getDetails/{type}/{id}", method = RequestMethod.GET)
    public String getEventDetails(@PathVariable String id, @PathVariable String type){

        session.setAttribute("comments", eventAndPlacesServ.getAllCommentByTypeAndId(Long.parseLong(id),type));
        if(type.equals("restaurant")){
            session.setAttribute("placeDetails",eventAndPlacesServ.getOneRestaurantById(Long.parseLong(id)));
            session.setAttribute("placeType","restaurant");
        }
        if(type.equals("hotel")){
            session.setAttribute("placeDetails",eventAndPlacesServ.getOneHotelById(Long.parseLong(id)));
            session.setAttribute("placeType","hotel");

        }if(type.equals("attraction")){
            session.setAttribute("placeDetails",eventAndPlacesServ.getOneAttractionById(Long.parseLong(id)));
            session.setAttribute("placeType","attraction");
        }

        return "/placeDetails";
    }

    @RequestMapping(value="/addComment/{type}", method = RequestMethod.POST)
    public String addComment(@PathVariable String type,@RequestParam String placeId, @RequestParam String commentContent,
                             @RequestParam String rateInComment){

        UserAccount userAcc = (UserAccount) session.getAttribute("loggedUser");

        if(type.equals("restaurant")){
            Restaurant restaurant = eventAndPlacesServ.getOneRestaurantById(Long.parseLong(placeId));
            eventAndPlacesServ.addComment(userAcc,null,restaurant,null,null,
                    rateInComment,commentContent);
        }

        if(type.equals("hotel")){
            Hotel hotel = eventAndPlacesServ.getOneHotelById(Long.parseLong(placeId));
            eventAndPlacesServ.addComment(userAcc,null,null,hotel,null,
                    rateInComment,commentContent);
        }

        if(type.equals("attraction")){
            Attraction attraction = eventAndPlacesServ.getOneAttractionById(Long.parseLong(placeId));
            eventAndPlacesServ.addComment(userAcc,null,null,null,attraction,
                    rateInComment,commentContent);
        }

        return "redirect:/place/getDetails/" + type + "/" + placeId;
    }
}
