package application.controller;

import application.model.UserAccount;
import application.model.UserProfile;
import application.service.UserAccountService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {


    @Autowired
    private HttpSession session;

    @Autowired
    private UserAccountService userAccountServ;

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(method = {RequestMethod.POST})
    public String submit(@Valid UserAccount userAcc, UserProfile userProf, BindingResult result,
                         Map<String, Object> model, HttpServletRequest request) {

        model.put("changePasswordMessage", "");
        String message;

        userAcc.setUserProf(userProf);

        message = userAccountServ.editUserAccount(userAcc, request.getParameter("newPassword"),
                request.getParameter("newPassword2"));


        if (message.equals("changePasswordGood")) {

            model.put("changePasswordMessage", "Hasło zostało zmienione");
            return "/account";
        }
        if (message.equals("noMatchPasswords")) {

            model.put("changePasswordMessage", "Błędne powtórzenie hasła");
            return "/account";
        }
        if (message.equals("toShortPasswords")) {

            model.put("changePasswordMessage", "Nowe hasło powinno mieć od 6 do 14 znaków");
            return "/account";
        }

        if (message.equals("badCurrentPassword")) {

            model.put("changePasswordMessage", "Obecne hasło nie prawidłowe");
            return "/account";
        }

        return "/account";

    }
}
