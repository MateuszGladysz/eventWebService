package application.controller;


import application.model.UserAccount;

import application.model.UserProfile;
import application.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/registry")
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationServ;


    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/registry", method = {RequestMethod.GET, RequestMethod.POST})
    public String registry() {
        return "registry";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String submit(UserAccount userAcc, UserProfile userProf, Map<String,Object> model) {

        model.put("isMail","");
        UserAccount userToSave = null;
        if (userRegistrationServ.isMailUsed(userAcc) == true) {

            System.out.println("mail został użyty");
            model.put("isMail","Mail został użyty");
            return "/registry";

        } else {

            userToSave = userAcc;
            userToSave.setUserProf(userProf);

            userRegistrationServ.saveUserAccount(userToSave);

            return "redirect:/";
        }

//
    }

}
