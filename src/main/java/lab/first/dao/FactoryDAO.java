package lab.first.dao;

public class FactoryDAO {

    private static FactoryDAO factoryDAO;

    public static FactoryDAO getInstance() {
        if (factoryDAO == null) {
            factoryDAO = new FactoryDAO();
            return factoryDAO;
        }
        return factoryDAO;
    }

    public DAO getAirshipDAO() {
        return AirshipDAOImpl.getInstance();
    }

    public DAO getClientDAO() {
        return ClientDAOImpl.getInstance();
    }

    public DAO getRouteDAO() {
        return RouteDAOImpl.getInstance();
    }

    public DAO getTicketDAO() {
        return TicketDAOImpl.getInstance();
    }

}
