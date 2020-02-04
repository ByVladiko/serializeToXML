package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab.first.dao.ClientDAOImpl;
import lab.first.dao.TicketDAOImpl;
import lab.first.model.Airship;
import lab.first.serialize.AirshipXmlImpl;

import java.util.UUID;

public class MainApp extends Application {

    public static void main(String[] args) {
        AirshipXmlImpl xml = new AirshipXmlImpl();
        xml.save(new Airship(UUID.fromString("88be61ce-086c-43bc-9eb8-b96df5894ae9"), "Aerobus", 100));
        xml.save(new Airship(UUID.fromString("40a81aa3-d160-4d6a-8f28-ecdd2b601630"), "Aeroflot", 150));
        xml.save(new Airship(UUID.fromString("75bf5072-ce2d-4662-bbe8-257c3035e43d"), "Tui", 250));
        TicketDAOImpl ticketDAO = new TicketDAOImpl();
        ClientDAOImpl clientDAO = new ClientDAOImpl();
        System.out.println(ticketDAO.getListByClient(clientDAO.getList().get(0)));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/route/list_routes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("List Routes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}