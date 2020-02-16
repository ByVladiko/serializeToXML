package lab.first.net;

import lab.first.dao.factory.FactoryDAOImpl;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    private static final String UNIC_BINDING_NAME = "server.factoryDAO";

    private ServerSocket server;

    public Server() {
        try {
            server = new ServerSocket(3345);
            beginDialog();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void beginDialog() throws IOException {

        System.out.println("Waiting for a client...");

        Socket client = server.accept();
        System.out.println("Connection accepted.");

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind(UNIC_BINDING_NAME, FactoryDAOImpl.getInstance());

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        try {
            while (client.isConnected());
        } catch (Exception e) {
            out.close();
            in.close();
            client.close();
            System.out.println("Connection closed");
            beginDialog();
        }
        out.close();
        in.close();
        client.close();
        server.close();
    }

}
