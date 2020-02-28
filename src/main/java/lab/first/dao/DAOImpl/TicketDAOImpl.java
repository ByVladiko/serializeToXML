package lab.first.dao.DAOImpl;

import airship.model.Ticket;
import lab.first.dao.DAO;
import lab.first.serialize.TicketXmlImpl;
import lab.first.serialize.Xml;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class TicketDAOImpl implements DAO<Ticket>, Serializable {

    private static final long serialVersionUID = 1L;

    private Xml xml = new TicketXmlImpl();;

    private static DAO ticketDAO;

    public static DAO getInstance() {
        if (ticketDAO == null) {
            ticketDAO = new TicketDAOImpl();
            return ticketDAO;
        }
        return ticketDAO;
    }

    @Override
    public void add(Ticket ticket) throws RemoteException {
        xml.save(ticket);
    }

    @Override
    public void remove(Ticket ticket) throws RemoteException  {
        xml.delete(ticket);
    }

    @Override
    public List<Ticket> getList() throws RemoteException  {
        return xml.read();
    }

//    public List<Ticket> getListByClient(Client client) {
//        return xml.getTicketsByClient(client);
//    }
}
