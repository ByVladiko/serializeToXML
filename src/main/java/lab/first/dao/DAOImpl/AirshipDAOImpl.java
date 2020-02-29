package lab.first.dao.DAOImpl;

import airship.dao.DAO;
import airship.model.Airship;
import lab.first.serialize.AirshipXmlImpl;
import lab.first.serialize.Xml;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AirshipDAOImpl extends UnicastRemoteObject implements DAO<Airship> {

    private Xml xml = new AirshipXmlImpl();

    private static DAO<Airship> airshipDAO;

    private AirshipDAOImpl() throws RemoteException {
    }

    public static DAO<Airship> getInstance() {
        if (airshipDAO == null) {
            try {
                airshipDAO = new AirshipDAOImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return airshipDAO;
        }
        return airshipDAO;
    }

    @Override
    public void add(Airship airship) throws RemoteException {
        xml.save(airship);
    }

    @Override
    public void remove(Airship airship) throws RemoteException {
        xml.delete(airship);
    }

    @Override
    public List<Airship> getList() throws RemoteException {
        return xml.read();
    }
}
