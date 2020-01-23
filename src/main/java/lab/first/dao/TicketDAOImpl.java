package lab.first.dao;

import lab.first.model.Ticket;
import lab.first.serialize.TicketXmlImpl;

import java.util.List;

public class TicketDAOImpl implements DAO<Ticket> {

    private TicketXmlImpl xml = new TicketXmlImpl();

    @Override
    public void add(Ticket ticket) {
        xml.save(ticket);
    }

    @Override
    public void update(Ticket ticket) {

    }

    @Override
    public void remove(Ticket ticket) {
        xml.delete(ticket);
    }

    @Override
    public Ticket getById(String id) {
        return null;
    }

    @Override
    public List<Ticket> getList() {
        return xml.read();
    }
}
