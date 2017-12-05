package application.repository;

import application.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    UserAccount findOneByEmail(String email);

}
