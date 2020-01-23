package lab.first.dao;

import lab.first.model.Client;
import lab.first.serialize.ClientXmlImpl;

import java.util.List;

public class ClientDAOImpl implements DAO<Client> {

    private ClientXmlImpl xml = new ClientXmlImpl();

    @Override
    public void add(Client client) {
        xml.save(client);
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void remove(Client client) {
        xml.delete(client);
    }

    @Override
    public Client getById(String id) {
        return null;
    }

    @Override
    public List<Client> getList() {
        return xml.read();
    }
}
