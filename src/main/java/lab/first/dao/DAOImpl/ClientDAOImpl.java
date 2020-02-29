package lab.first.dao.DAOImpl;

import airship.dao.DAO;
import airship.model.Client;
import lab.first.serialize.ClientXmlImpl;
import lab.first.serialize.Xml;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClientDAOImpl extends UnicastRemoteObject implements DAO<Client> {

    private Xml xml = new ClientXmlImpl();

    private static DAO<Client> clientDAO;

    private ClientDAOImpl() throws RemoteException {
    }

    public static DAO<Client> getInstance() {
        if (clientDAO == null) {
            try {
                clientDAO = new ClientDAOImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return clientDAO;
        }
        return clientDAO;
    }

    @Override
    public void add(Client client) throws RemoteException {
        xml.save(client);
    }

    @Override
    public void remove(Client client) throws RemoteException  {
        xml.delete(client);
    }

    @Override
    public List<Client> getList() throws RemoteException  {
        return xml.read();
    }
}
