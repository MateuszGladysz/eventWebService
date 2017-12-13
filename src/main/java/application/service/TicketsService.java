package application.service;


import application.model.Ticket;
import application.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class TicketsService {

    @Autowired
    private HttpSession session;

    @Autowired
    TicketRepository ticketRepo;

    public List<Ticket> getTicketByOwnerId(long ownerId){return ticketRepo.findAllByOwnerId(ownerId);}
}
