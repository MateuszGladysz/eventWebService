package application.controller;


import application.model.UserAccount;

import application.model.UserProfile;
import application.service.UserAccountService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
    private UserAccountService userAccountServ;


    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/registry", method = {RequestMethod.GET})
    public String registry() {
        return "registry";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String submit(UserAccount userAcc, UserProfile userProf, Map<String,Object> model,
    HttpServletRequest request) {


        model.put("isMail","");
        model.put("registerMessage","");
        userAcc.setUserProf(userProf);

        if (userAccountServ.isMailUsed(userAcc) == true) {

            model.put("isMail","Konto dla adresu: "+ userAcc.getEmail() + " już istnieje");
            return "/registry";

        }
        if(!userAccountServ.isText(userAcc.getUserProf().getFirstName())
                || !userAccountServ.isText(userAcc.getUserProf().getLastName())){
            model.put("isText", "Imię oraz Nazwisko powinno zawierać wyłącznie litery");
            return "/registry";
        } else{


            String message;
            message = userAccountServ.addUser(userAcc, request.getParameter("password1"));

            if(message.equals("registrationGood")){
                session.setAttribute("registerGoodMessage", "Rejestracja przebiegła pomyślnie, Witamy.");
                return "redirect:/login";
            }

            if(message.equals("noMatchPasswords")){
                model.put("registerMessage", "Błędne powtórzenie hasła");
                return "/registry";
            }
            if(message.equals("badSizePassword")){
                model.put("registerMessage", "Nieprawidłowy rozmiar hasła(wymagane od 5 do 15 znaków)");
                return "/registry";
            }


            return "/registry";
        }

//
    }

}
