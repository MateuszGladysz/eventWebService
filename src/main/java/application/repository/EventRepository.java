package application.repository;

import application.model.Event;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface EventRepository extends CrudRepository<Event, Long> {

    Event findOneById(long id);
    List<Event> findAllByEventType(String type);

}
