package application.repository;

import application.model.Comment;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByEventId(long id);
    List<Comment> findAllByRestaurantId(long id);
    List<Comment> findAllByHotelId(long id);
    List<Comment> findAllByAttractionId(long id);


}
