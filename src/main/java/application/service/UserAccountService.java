package application.service;


import application.model.UserAccount;
import application.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepo;

    @Autowired
    private HttpSession session;

    public String login(String email, String password) {


        if (userAccountRepo.findOneByEmail(email) != null) {

            UserAccount userAcc = userAccountRepo.findOneByEmail(email);
            if (userAcc.getPassword().equals(password)) {

                if(userAcc.getUserProf().getWorker() == true) return "workerlogged";
                else return "logged";

            } else {
                return "notlogged";
            }
        } else {
            return "noAccountWithThisEmail";
        }

    }

    public UserAccount getUser(String email) {

        UserAccount userAcc = userAccountRepo.findOneByEmail(email);
        return userAcc;
    }


    public String editUserAccount(UserAccount userAcc, String newPassword, String newPassword2) {

        UserAccount userFromSession = (UserAccount) session.getAttribute("loggedUser");

        if (!userAcc.getUserProf().getFirstName().equals(""))
            userFromSession.getUserProf().setFirstName(userAcc.getUserProf().getFirstName());
        if (!userAcc.getUserProf().getLastName().equals(""))
            userFromSession.getUserProf().setLastName(userAcc.getUserProf().getLastName());
        if (!userAcc.getUserProf().getAddress().equals(""))
            userFromSession.getUserProf().setAddress(userAcc.getUserProf().getAddress());
        if (!userAcc.getUserProf().getPostCode().equals(""))
            userFromSession.getUserProf().setPostCode(userAcc.getUserProf().getPostCode());
        if (!userAcc.getUserProf().getCity().equals(""))
            userFromSession.getUserProf().setCity(userAcc.getUserProf().getCity());
        if (!userAcc.getUserProf().getPhoneNumber().equals(""))
            userFromSession.getUserProf().setPhoneNumber(userAcc.getUserProf().getPhoneNumber());
        if (!userAcc.getEmail().equals(""))
            userFromSession.setEmail(userAcc.getEmail());

        if (!userAcc.getPassword().equals("")) {
            if (userAcc.getPassword().equals(userFromSession.getPassword())) {

                String message;
                message = passwordChangeValidation(newPassword, newPassword2);

                if (message.equals("changePasswordGood")) {
                    userFromSession.setPassword(newPassword);
                    session.setAttribute("loggedUser", userFromSession);

                    userAccountRepo.save(userFromSession);

                    return "changePasswordGood";
                } else return message;

            } else {

                return "badCurrentPassword";
            }

        }
        session.setAttribute("loggedUser", userFromSession);
        userAccountRepo.save(userFromSession);
        return "";
    }

    public String addUser(UserAccount userAcc, String passwordToCheck) {

            if (passwordChangeValidation(userAcc.getPassword()
                    , passwordToCheck).equals("changePasswordGood")) {

                userAccountRepo.save(userAcc);
                return "registrationGood";

            } else return "noMatchPasswords";

    }





    public String passwordChangeValidation(String password1, String password2) {

        if (password1.length() > 5 && password1.length() < 15 &&
                password2.length() > 5 && password2.length() < 15) {

            if (password1.equals(password2)) return "changePasswordGood";
            else return "noMatchPasswords";

        } else return "toShortPasswords";

    }

    public boolean isMailUsed(UserAccount userAcc) {

        if (userAccountRepo.findOneByEmail(userAcc.getEmail()) != null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isText(String name){

        String template = "[A-ZĄĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+";
        CharSequence nameSeq = name;
        Pattern pattern = Pattern.compile(template, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nameSeq);
        return matcher.matches();

    }

}
