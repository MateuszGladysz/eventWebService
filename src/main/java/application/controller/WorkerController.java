package application.controller;


import application.model.Event;
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


    @RequestMapping(value = {"/addEvent"}, method = RequestMethod.POST)
    public String addEvent(Event event, @RequestParam("eventPhotoFile") MultipartFile eventPhoto, Map<String, Object> model) {

        System.out.println(event.getEventName() + " " + event.getEventDescription());
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

}
