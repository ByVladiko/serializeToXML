package lab.first.dao;

import lab.first.model.Client;
import lab.first.model.Ticket;
import lab.first.serialize.TicketXmlImpl;

import java.util.List;

public class TicketDAOImpl implements DAO<Ticket> {

    private TicketXmlImpl xml = new TicketXmlImpl();;

    private static TicketDAOImpl ticketDAO;

    public static TicketDAOImpl getInstance() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAOImpl();
            return ticketDAO;
        }
        return ticketDAO;
    }

    @Override
    public void add(Ticket ticket) {
        xml.save(ticket);
    }

    public void addToClient(Ticket ticket, Client client) {

    }

    @Override
    public void remove(Ticket ticket) {
        xml.delete(ticket);
    }

    @Override
    public List<Ticket> getList() {
        return xml.read();
    }

    public List<Ticket> getListByClient(Client client) {
        return xml.getTicketsByClient(client);
    }
}
