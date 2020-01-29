package lab.first.dao;

import lab.first.model.Airship;
import lab.first.serialize.AirshipXmlImpl;

import java.util.List;

public class AirshipDAOImpl implements DAO<Airship> {

    private AirshipXmlImpl xml = new AirshipXmlImpl();

    private static AirshipDAOImpl airshipDAO;

    public static AirshipDAOImpl getInstance() {
        if (airshipDAO == null) {
            airshipDAO = new AirshipDAOImpl();
            return airshipDAO;
        }
        return airshipDAO;
    }

    @Override
    public void add(Airship airship) {
        xml.save(airship);
    }

    @Override
    public void remove(Airship airship) {
        xml.delete(airship);
    }

    @Override
    public List<Airship> getList() {
        return xml.read();
    }
}
