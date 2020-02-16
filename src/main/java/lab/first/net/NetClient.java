package lab.first.net;

import lab.first.dao.factory.FactoryDAO;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NetClient {

    private static NetClient netClient;
    private static final String UNIC_BINDING_NAME = "server.factoryDAO";

    private final Registry registry = LocateRegistry.getRegistry(1099);

    private Socket socket;
    private BufferedWriter bw;

    public static NetClient getInstance() throws RemoteException {
        if (netClient == null) {
            netClient = new NetClient();
            return netClient;
        }
        return netClient;
    }

    public NetClient() throws RemoteException {
        try {
            socket = new Socket("localhost", 3345);
            System.out.println("Connected!");
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FactoryDAO getFactoryDAO() throws IOException, NotBoundException {

        if (socket.isConnected()) {
            return (FactoryDAO) registry.lookup(UNIC_BINDING_NAME);
        } else {
            return null;
        }
    }
}
