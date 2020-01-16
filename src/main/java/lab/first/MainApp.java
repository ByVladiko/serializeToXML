package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab.first.model.Airship;
import lab.first.model.Client;
import lab.first.model.Ticket;
import lab.first.serialize.ClientXmlImpl;
import lab.first.serialize.RouteXmlImpl;
import lab.first.serialize.Xml;
import lab.first.view.ConverterToFX;
import lab.first.view.controllers.client.ClientListController;
import lab.first.view.controllers.route.RouteListController;
import lab.first.model.Route;
import lab.first.view.controllers.ticket.TicketListController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
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

        Ticket ticket1 = new Ticket(airship1, route1);
        Ticket ticket2 = new Ticket(airship2, route2);
        Ticket ticket3 = new Ticket(airship3, route3);
        Ticket ticket4 = new Ticket(airship2, route2);
        Ticket ticket5 = new Ticket(airship2, route1);
        Ticket ticket6 = new Ticket(airship1, route3);

        TicketListController.tableTickets.add(ticket1);
        TicketListController.tableTickets.add(ticket2);
        TicketListController.tableTickets.add(ticket3);
        TicketListController.tableTickets.add(ticket4);
        TicketListController.tableTickets.add(ticket5);
        TicketListController.tableTickets.add(ticket6);

        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);

        client1.setTickets(tickets);

        JAXBContext context = JAXBContext.newInstance(Client.class);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringWriter writer = new StringWriter();
//        System.out.println(unmarshaller.unmarshal(new StringReader("<ticket id=\"99665c72-01cc-4deb-8fdb-aa8f6cf634e8\"><airship id=\"531df6cd-447b-4943-9e93-6fb1b20ce070\"><model>Aeroflot</model><numberOfSeat>3</numberOfSeat></airship><route id=\"b85a1492-9b27-487d-82bd-a9b67505ae61\"><startPoint>Kostroma</startPoint><endPoint>Saratov</endPoint></route></ticket>")));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        marshaller.marshal(client1, writer);
//        System.out.println(writer);
        StringReader sr = new StringReader(writer.toString());
        Client client = (Client) unmarshaller.unmarshal(sr);
//        System.out.println(client);


        Xml serial = new RouteXmlImpl();
//        serial.save(route1);
//        serial.save(route2);
        serial.delete(route1);
//        for (int i = 0; i < serial.read().size(); i++) {
//            System.out.println(serial.read().get(i).toString());
//        }
//        Xml serialTicket = new TicketXmlImpl();
//        serialTicket.read();

        Xml serialClient = new ClientXmlImpl();
        serialClient.save(client1);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/route/list_routes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("List Routes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}