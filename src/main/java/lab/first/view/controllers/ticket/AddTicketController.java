package lab.first.view.controllers.ticket;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lab.first.dao.AirshipDAOImpl;
import lab.first.dao.RouteDAOImpl;
import lab.first.dao.TicketDAOImpl;
import lab.first.model.Airship;
import lab.first.model.Client;
import lab.first.model.Route;
import lab.first.model.Ticket;
import lab.first.view.controllers.MainControl;

import java.net.URL;
import java.util.ResourceBundle;

import static lab.first.view.controllers.Util.toScene;

public class AddTicketController extends MainControl implements Initializable {

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

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
        if (client != null) {
            Ticket ticket = new Ticket(airshipChoiceBox.getValue(), routeChoiceBox.getValue());
            factoryDAO.getTicketDAO().add(ticket);
            client.getTickets().add(ticket);
            client.setTickets(client.getTickets());
            factoryDAO.getClientDAO().add(client);
            TicketListController ticketListController = new TicketListController();
            ticketListController.setClient(client);
            toScene("ticket/list_tickets.fxml", "List Tickets");
        }
        else {
            Ticket ticket = new Ticket(airshipChoiceBox.getValue(), routeChoiceBox.getValue());
            factoryDAO.getTicketDAO().add(ticket);
            toScene("ticket/list_tickets.fxml", "List Tickets");
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RouteDAOImpl routeDAO = new RouteDAOImpl();
        AirshipDAOImpl airshipDAO = new AirshipDAOImpl();
        routeChoiceBox.setItems(FXCollections.observableArrayList(routeDAO.getList()));
        airshipChoiceBox.setItems(FXCollections.observableArrayList(airshipDAO.getList()));
    }
}

