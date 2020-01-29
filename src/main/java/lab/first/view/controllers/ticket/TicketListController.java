package lab.first.view.controllers.ticket;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import lab.first.dao.TicketDAOImpl;
import lab.first.model.Client;
import lab.first.model.Ticket;
import lab.first.view.controllers.MainControl;

import static lab.first.view.controllers.Util.toScene;

public class TicketListController extends MainControl implements Initializable {

    protected ObservableList<Ticket> tableTickets = FXCollections.observableArrayList();

    private Client client;

    static TicketDAOImpl dao = new TicketDAOImpl();

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
        toScene("ticket/new_ticket.fxml", "New Ticket");
    }

    @FXML
    void deleteTicketButtonAction(ActionEvent event) {
        if (tableViewTickets.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        dao.remove(tableViewTickets.getSelectionModel().getSelectedItem());
        refreshTable();
    }

    @FXML
    void editTicketButtonAction(ActionEvent event) {

    }

    @FXML
    void idInputTextFieldAction(InputMethodEvent event) {

    }

    private void refreshTable() {
        if (client.getId() == null) {
            tableTickets.setAll(dao.getList());
            tableViewTickets.setItems(tableTickets);
        } else {
            tableTickets.setAll(dao.getListByClient(client));
            tableViewTickets.setItems(tableTickets);
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableTicketColumnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        tableTicketColumnAirship.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAirship().getModel()));
        tableTicketColumnRouteFrom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoute().getStartPoint()));
        tableTicketColumnRouteTo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoute().getEndPoint()));

        refreshTable();
    }
}
