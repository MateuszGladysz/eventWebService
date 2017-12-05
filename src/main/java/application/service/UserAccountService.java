package application.service;


import application.model.UserAccount;
import application.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepo;

    public String login(String email, String password) {


        if (userAccountRepo.findOneByEmail(email) != null) {

            UserAccount userAcc = userAccountRepo.findOneByEmail(email);
            if (userAcc.getPassword().equals(password)) {
                return "zalogowano";
            } else {
                return "niezalogowano";
            }
        } else {
            return "brak podanego email";
        }

    }

    public UserAccount getUser(String email){

        UserAccount userAcc = userAccountRepo.findOneByEmail(email);
        return userAcc;
    }




}
