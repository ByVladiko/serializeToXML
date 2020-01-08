package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab.first.model.Airship;
import lab.first.model.Client;
import lab.first.model.Ticket;
import lab.first.view.ConverterToFX;
import lab.first.view.controllers.client.ClientListController;
import lab.first.view.controllers.route.RouteListController;
import lab.first.model.Route;
import lab.first.view.controllers.ticket.TicketListController;
import java.util.ArrayList;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ConverterToFX converter = new ConverterToFX();

        Route route1 = new Route("L.A.", "Moscow");
        Route route2 = new Route("Kostroma", "Saratov");
        Route route3 = new Route("Tolyatti", "Chelyabinsk");

        RouteListController.tableRoutes.add(route1);
        RouteListController.tableRoutes.add(route2);
        RouteListController.tableRoutes.add(route3);

        Client client1 = new Client("Arkadii", "Eremeev", "Anatolevich");
        Client client2 = new Client("Viktor", "Prokofev", "Gennadievich");
        Client client3 = new Client("Leonid", "Gerasimov", "Sergeevich");

        ClientListController.tableClients.add(client1);
        ClientListController.tableClients.add(client2);
        ClientListController.tableClients.add(client3);

        Airship airship1 = new Airship("Aerobus", 5);
        Airship airship2 = new Airship("Aeroflot", 3);
        Airship airship3 = new Airship("Migrate", 5);

        Airship.airships.add(airship1);
        Airship.airships.add(airship2);
        Airship.airships.add(airship3);

        TicketListController.tableTickets.add(new Ticket(airship1, route1));
        TicketListController.tableTickets.add(new Ticket(airship2, route2));
        TicketListController.tableTickets.add(new Ticket(airship3, route3));
        TicketListController.tableTickets.add(new Ticket(airship2, route2));
        TicketListController.tableTickets.add(new Ticket(airship2, route1));
        TicketListController.tableTickets.add(new Ticket(airship1, route3));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/route/list_routes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("List Routes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}