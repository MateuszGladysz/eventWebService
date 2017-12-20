package application.repository;


import application.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface TicketRepository extends CrudRepository<Ticket, Long>{


    List<Ticket> findAllByOwnerId(long ownerId);
    List<Ticket> findAll();


}