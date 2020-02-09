package lab.first.view.controllers.ticket;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lab.first.dao.AirshipDAOImpl;
import lab.first.dao.ClientDAOImpl;
import lab.first.dao.RouteDAOImpl;
import lab.first.model.Airship;
import lab.first.model.Client;
import lab.first.model.Route;
import lab.first.model.Ticket;
import lab.first.view.controllers.MainControl;

public class AddTicketController extends MainControl implements Initializable {

    static Client client;

    @FXML
    private Button mainRoutesButton;

    @FXML
    private Button mainTicketsButton;

    @FXML
    private Button mainClientsButton;

    @FXML
    private ComboBox<Route> routeChoiceBox;

    @FXML
    private ComboBox<Airship> airshipChoiceBox;

    @FXML
    private Button saveTicketButton;

    @FXML
    void saveTicketButtonAction(ActionEvent event) throws Exception {
        if (routeChoiceBox.getValue() == null || airshipChoiceBox.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Attention");
            a.setHeaderText("Ops!");
            String version = System.getProperty("java.version");
            String content = String.format("The choice boxes should be not empty", version);
            a.setContentText(content);
            a.showAndWait();
            return;
        }
            Ticket newTicket = new Ticket(airshipChoiceBox.getValue(), routeChoiceBox.getValue());
            List<Ticket> tickets = client.getTickets();
            tickets.add(newTicket);
            client.setTickets(tickets);
            ClientDAOImpl.getInstance().add(client);
            TicketListController.client = client;
            toScene("ticket/list_tickets.fxml", "List Tickets", event);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RouteDAOImpl routeDAO = new RouteDAOImpl();
        AirshipDAOImpl airshipDAO = new AirshipDAOImpl();
        routeChoiceBox.setItems(FXCollections.observableArrayList(routeDAO.getList()));
        airshipChoiceBox.setItems(FXCollections.observableArrayList(airshipDAO.getList()));
    }
}

