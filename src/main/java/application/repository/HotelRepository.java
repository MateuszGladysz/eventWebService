package application.repository;


import application.model.Hotel;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface HotelRepository extends CrudRepository<Hotel, Long> {
}
