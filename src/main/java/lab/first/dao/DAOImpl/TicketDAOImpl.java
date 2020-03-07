package lab.first.dao.DAOImpl;

import airship.dao.DAO;
import airship.model.Ticket;
import lab.first.serialize.TicketXmlImpl;
import lab.first.serialize.Xml;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TicketDAOImpl extends UnicastRemoteObject implements DAO<Ticket> {

    private Xml<Ticket> xml = new TicketXmlImpl();;

    private static DAO<Ticket> ticketDAO;

    private TicketDAOImpl() throws RemoteException {
    }

    public static DAO<Ticket> getInstance() {
        if (ticketDAO == null) {
            try {
                ticketDAO = new TicketDAOImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return ticketDAO;
        }
        return ticketDAO;
    }

    @Override
    public boolean add(Ticket ticket) throws RemoteException {
        return xml.save(ticket);
    }

    @Override
    public boolean remove(Ticket ticket) throws RemoteException  {
        return xml.delete(ticket);
    }

    @Override
    public List<Ticket> getList() throws RemoteException  {
        return xml.read();
    }
}
