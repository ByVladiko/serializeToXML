package lab.first.dao.DAOImpl;

import airship.dao.DAO;
import airship.model.Ticket;
import lab.first.serialize.TicketXmlImpl;
import lab.first.serialize.Xml;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TicketDAOImpl extends UnicastRemoteObject implements DAO<Ticket> {

    private Xml xml = new TicketXmlImpl();;

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
}
