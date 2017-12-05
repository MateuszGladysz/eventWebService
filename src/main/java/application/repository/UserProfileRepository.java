package application.repository;

import application.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface UserProfileRepository extends CrudRepository<UserAccount, Long>{


}
