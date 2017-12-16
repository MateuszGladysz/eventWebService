package application.repository;


import application.model.Attraction;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface AttractionRepository extends CrudRepository<Attraction, Long> {

    Attraction findOneById(long id);
    List<Attraction> findAllByAttractionCity(String city);
}
