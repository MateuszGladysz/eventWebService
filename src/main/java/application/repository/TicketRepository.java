package application.repository;


import application.model.Ticket;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;



@Transactional
public interface TicketRepository extends CrudRepository<Ticket, Long>{

}