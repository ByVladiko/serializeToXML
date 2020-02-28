package lab.first.dao.DAOImpl;

import airship.model.Client;
import lab.first.dao.DAO;
import lab.first.serialize.ClientXmlImpl;
import lab.first.serialize.Xml;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class ClientDAOImpl implements DAO<Client>, Serializable {

    private static final long serialVersionUID = 1L;

    private Xml xml = new ClientXmlImpl();

    private static DAO clientDAO;

    public static DAO getInstance() {
        if (clientDAO == null) {
            clientDAO = new ClientDAOImpl();
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
