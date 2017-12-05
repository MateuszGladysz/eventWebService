package application.service;

import application.model.UserAccount;

import application.repository.UserAccountRepository;
import application.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {


    @Autowired
    UserProfileRepository userProfileRepo;

    @Autowired
    UserAccountRepository userAccountRepo;

    public String saveUserAccount(UserAccount userAcc){

        userProfileRepo.save(userAcc);

        return "zapisano";
    }

    public boolean isMailUsed(UserAccount userAcc) {

        if (userAccountRepo.findOneByEmail(userAcc.getEmail()) != null) {
            return true;
        } else {
            return false;
        }

    }
}
