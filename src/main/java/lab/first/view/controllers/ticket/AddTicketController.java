package lab.first.view.controllers.ticket;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lab.first.dao.AirshipDAOImpl;
import lab.first.dao.RouteDAOImpl;
import lab.first.model.Airship;
import lab.first.model.Route;
import lab.first.model.Ticket;
import lab.first.view.controllers.route.RouteListController;

public class AddTicketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private void mainRoutesButtonAction(ActionEvent event) throws Exception {
        toScene("route/list_routes.fxml", "List Routes", event);
    }

    @FXML
    private void mainClientsButtonAction(ActionEvent event) throws Exception {
        toScene("client/list_clients.fxml", "List Clients", event);
    }

    @FXML
    private void mainTicketsButtonAction(ActionEvent event) throws Exception {
        toScene("ticket/list_tickets.fxml", "List Tickets", event);
    }

    private void toScene(String path, String title, ActionEvent event) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();

        Parent root = FXMLLoader.load(getClass().getResource("../../../../../fxml/" + path));
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void saveRouteButtonAction(ActionEvent event) throws Exception {
        if(routeChoiceBox.getValue() == null || airshipChoiceBox.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Attention");
            a.setHeaderText("Ops!");
            String version = System.getProperty("java.version");
            String content = String.format("The choice boxes should be not empty", version);
            a.setContentText(content);
            a.showAndWait();
            return;
        }
        TicketListController.dao.add(new Ticket(airshipChoiceBox.getValue(), routeChoiceBox.getValue()));
        toScene("ticket/list_tickets.fxml", "List Tickets", event);
    }

    @FXML
    void initialize() {
        RouteDAOImpl routeDAO = new RouteDAOImpl();
        AirshipDAOImpl airshipDAO = new AirshipDAOImpl();
        routeChoiceBox.setItems(FXCollections.observableArrayList(routeDAO.getList()));
        airshipChoiceBox.setItems(FXCollections.observableArrayList(airshipDAO.getList()));
    }
}

