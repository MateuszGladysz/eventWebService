package application.controller;


import application.model.Attraction;
import application.model.Event;
import application.model.Hotel;
import application.model.Restaurant;
import application.service.EventAndPlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Map;


@Controller
@RequestMapping("/workerPanel")
public class WorkerController {

    @Autowired
    EventAndPlacesService eventAndPlacesServ;


    @RequestMapping(value = "/addEvent", method = RequestMethod.GET)
    public String event() {
        return "addEvent";
    }

    @RequestMapping(value = "/addRestaurant", method = RequestMethod.GET)
    public String restaurant() {
        return "addRestaurant";
    }

    @RequestMapping(value = "/addHotel", method = RequestMethod.GET)
    public String hotel() {
        return "addHotel";
    }

    @RequestMapping(value = "/addAttraction", method = RequestMethod.GET)
    public String attraction() {
        return "addAttraction";
    }


    @RequestMapping(value = {"/addEvent"}, method = RequestMethod.POST)
    public String addEvent(Event event, @RequestParam("eventPhotoFile") MultipartFile eventPhoto, Map<String, Object> model) {


        if (!eventPhoto.getOriginalFilename().contains(".jpg")) {
            model.put("addingEventFailureMessage", "Proszę podać plik z roszerzeniem jpg");
            return "/addEvent";
        } else {
            try {
                this.eventAndPlacesServ.addEvent(event, eventPhoto);
                return "redirect:/workerPanel";
            } catch (IOException var5) {
                model.put("addingEventFailureMessage", "Problem z zapisem do bazy");
                return "/addEvent";
            }
        }
    }

    @RequestMapping(value = {"/addRestaurant"}, method = RequestMethod.POST)
    public String addRestaurant(Restaurant restaurant, @RequestParam("restaurantPhotoFile") MultipartFile restaurantPhoto, Map<String, Object> model) {


        if (!restaurantPhoto.getOriginalFilename().contains(".jpg")) {
            model.put("addingRestaurantFailureMessage", "Proszę podać plik z roszerzeniem jpg");
            return "/addRestaurant";
        } else {
            try {
                this.eventAndPlacesServ.addRestaurant(restaurant, restaurantPhoto);
                return "redirect:/workerPanel";
            } catch (IOException var5) {
                model.put("addingRestaurantFailureMessage", "Problem z zapisem do bazy");
                return "/addRestaurant";
            }
        }
    }


    @RequestMapping(value = {"/addHotel"}, method = RequestMethod.POST)
    public String addHotel(Hotel hotel, @RequestParam("hotelPhotoFile") MultipartFile hotelPhoto, Map<String, Object> model) {


        if (!hotelPhoto.getOriginalFilename().contains(".jpg")) {
            model.put("addingHotelFailureMessage", "Proszę podać plik z roszerzeniem jpg");
            return "/addHotel";
        } else {
            try {
                this.eventAndPlacesServ.addHotel(hotel, hotelPhoto);
                return "redirect:/workerPanel";
            } catch (IOException var5) {
                model.put("addingHotelFailureMessage", "Problem z zapisem do bazy");
                return "/addHotel";
            }
        }
    }

    @RequestMapping(value = {"/addAttraction"}, method = RequestMethod.POST)
    public String addAttraction(Attraction attraction, @RequestParam("attractionPhotoFile") MultipartFile attractionPhoto, Map<String, Object> model) {


        if (!attractionPhoto.getOriginalFilename().contains(".jpg")) {
            model.put("addingAttractionFailureMessage", "Proszę podać plik z roszerzeniem jpg");
            return "/addAttraction";
        } else {
            try {
                this.eventAndPlacesServ.addAttraction(attraction, attractionPhoto);
                return "redirect:/workerPanel";
            } catch (IOException var5) {
                model.put("addingAttractionFailureMessage", "Problem z zapisem do bazy");
                return "/addAttraction";
            }
        }
    }




}
