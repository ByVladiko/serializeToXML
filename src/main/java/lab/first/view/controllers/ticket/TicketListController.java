package lab.first.view.controllers.ticket;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import lab.first.dao.TicketDAOImpl;
import lab.first.model.Ticket;
import lab.first.view.ConverterToFX;

public class TicketListController {

    private ConverterToFX converter = new ConverterToFX();

    private ObservableList<Ticket> tableTickets  = FXCollections.observableArrayList();

    protected static TicketDAOImpl dao = new TicketDAOImpl();

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
    private TextField idTextField;

    @FXML
    private TextField firstAirshipTextField;

    @FXML
    private TextField routeFromTextField;

    @FXML
    private TextField routeToTextField;

    @FXML
    private Button addTicketButton;

    @FXML
    private Button editTicketButton;

    @FXML
    private Button deleteTicketButton;

    @FXML
    private TableView<Ticket> tableViewTickets;

    @FXML
    private TableColumn<Ticket, String> tableTicketColumnId;

    @FXML
    private TableColumn<Ticket, String> tableTicketColumnAirship;

    @FXML
    private TableColumn<Ticket, String> tableTicketColumnRouteFrom;

    @FXML
    private TableColumn<Ticket, String> tableTicketColumnRouteTo;

    @FXML
    void addTicketButtonAction(ActionEvent event) throws Exception {
        toScene("ticket/new_ticket.fxml", "New Ticket", event);
    }

    @FXML
    void deleteTicketButtonAction(ActionEvent event) {
        UUID id = null;
        if (tableViewTickets.getSelectionModel().getSelectedItem() != null) {
            id = (UUID) tableViewTickets.getSelectionModel().getSelectedItem().getId();
        } else {
            return;
        }
        for (int i = 0; i < tableTickets.size(); i++) {
            if (tableTickets.get(i).getId() == id) {
                tableTickets.remove(i);
            }
        }
        refreshTable();
    }

    @FXML
    void editTicketButtonAction(ActionEvent event) {

    }

    @FXML
    void idInputTextFieldAction(InputMethodEvent event) {

    }

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

    private void refreshTable() {
        tableViewTickets.setItems(tableTickets);
    }

    @FXML
    void initialize() {

        tableTicketColumnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        tableTicketColumnAirship.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAirship().getModel()));
        tableTicketColumnRouteFrom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoute().getStartPoint()));
        tableTicketColumnRouteTo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoute().getEndPoint()));

        refreshTable();
    }
}
