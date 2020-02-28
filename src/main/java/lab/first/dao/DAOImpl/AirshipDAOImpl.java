package lab.first.dao.DAOImpl;

import airship.model.Airship;
import airship.dao.DAO;
import lab.first.serialize.AirshipXmlImpl;
import lab.first.serialize.Xml;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class AirshipDAOImpl implements DAO<Airship>, Serializable {

    private static final long serialVersionUID = 1L;

    private Xml xml = new AirshipXmlImpl();

    private static DAO airshipDAO;

    public static DAO getInstance() {
        if (airshipDAO == null) {
            airshipDAO = new AirshipDAOImpl();
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
