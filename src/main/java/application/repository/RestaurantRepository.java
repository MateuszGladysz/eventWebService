package application.repository;


import application.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
