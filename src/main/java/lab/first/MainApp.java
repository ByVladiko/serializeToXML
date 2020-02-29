package lab.first;

import airship.dao.FactoryDAO;
import airship.model.Airship;
import lab.first.dao.factory.FactoryDAOImpl;
import lab.first.serialize.AirshipXmlImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class MainApp {

    private static final String LOCALHOST_IP = "127.0.0.1";
    private static final String REMOTE_HOSTNAME = "java.rmi.server.hostname";
    private static final String REMOTE_SERVICE_NAME = "FactoryDAO";
    private static final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        AirshipXmlImpl xml = new AirshipXmlImpl();
        xml.save(new Airship(UUID.fromString("88be61ce-086c-43bc-9eb8-b96df5894ae9"), "Aerobus", 100));
        xml.save(new Airship(UUID.fromString("40a81aa3-d160-4d6a-8f28-ecdd2b601630"), "Aeroflot", 150));
        xml.save(new Airship(UUID.fromString("75bf5072-ce2d-4662-bbe8-257c3035e43d"), "Tui", 250));

        try {
            System.setProperty(REMOTE_HOSTNAME, LOCALHOST_IP);
            System.out.println("Initialization...");
            System.out.println("Creation remote service instance...");

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            System.out.println("Creation done");
            System.out.println(factoryDAO.getAirshipDAO().getList().get(0));

            System.out.println("Remote service instance adding to RMI registry");
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.bind(REMOTE_SERVICE_NAME, factoryDAO);
            System.out.println("Done");
            System.out.println("Server is waiting for requests...");
        } catch (AlreadyBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}