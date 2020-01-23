package lab.first.dao;

import lab.first.model.Airship;
import lab.first.serialize.AirshipXmlImpl;

import java.util.List;

public class AirshipDAOImpl implements DAO<Airship> {

    private AirshipXmlImpl xml = new AirshipXmlImpl();

    @Override
    public void add(Airship airship) {
        xml.save(airship);
    }

    @Override
    public void update(Airship airship) {

    }

    @Override
    public void remove(Airship airship) {
        xml.delete(airship);
    }

    @Override
    public Airship getById(String id) {
        return null;
    }

    @Override
    public List<Airship> getList() {
        return xml.read();
    }
}
