package application.controller;

import application.model.UserAccount;
import application.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserAccountService userAccountServ;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String submit(UserAccount account, Map<String,Object> model) {

        String message;
        UserAccount userAcc;

            model.put("loginFailureMessage","");
            message = userAccountServ.login(account.getEmail(), account.getPassword());
            userAcc = userAccountServ.getUser(account.getEmail());


        if(message.equals("workerlogged")){

            System.out.println("Worker zalogowany");
            userAcc.getUserProf().setWorker(true);
            session.setAttribute("loggedUser", userAcc);
            return "/workerPanel";
        }
        if(message.equals("logged")){
            session.setAttribute("loggedUser", userAcc);
            return "redirect:/";
        }
        if(message.equals("notlogged")){
            model.put("loginFailureMessage","Niezalogowano, hasło jest błędne");
            return "/login";
        }
        if(message.equals("noAccountWithThisEmail")){
            model.put("loginFailureMessage","Niezalogowano, brak konta o podanym adresie E-mail");
            return "/login";
        }

        return "redirect:/";
    }
}
