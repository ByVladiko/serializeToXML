package lab.first.view.controllers.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lab.first.dao.ClientDAOImpl;
import lab.first.model.Client;
import lab.first.view.controllers.MainControl;
import lab.first.view.controllers.ticket.TicketListController;

import static lab.first.view.controllers.Util.toScene;

public class ClientListController extends MainControl implements Initializable {

    private ObservableList<Client> tableClients  = FXCollections.observableArrayList();

    static ClientDAOImpl dao = new ClientDAOImpl();

    @FXML
    private Button mainRoutesButton;

    @FXML
    private Button mainTicketsButton;

    @FXML
    private Button mainClientsButton;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField middleNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button addClientButton;

    @FXML
    private Button editClientButton;

    @FXML
    private Button deleteClientButton;

    @FXML
    private TableView<Client> tableViewClients;

    @FXML
    private TableColumn<Client, String> tableClientColumnId;

    @FXML
    private TableColumn<Client, String> tableClientColumnFirstName;

    @FXML
    private TableColumn<Client, String> tableClientsColumnMiddleName;

    @FXML
    private TableColumn<Client, String> tableClientsColumnLastName;

    @FXML
    void addClientButtonAction(ActionEvent event) throws Exception {
        toScene("client/new_client.fxml", "New Client");
    }

    @FXML
    void deleteClientButtonAction(ActionEvent event) {
        if (tableViewClients.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        dao.remove(tableViewClients.getSelectionModel().getSelectedItem());
        refreshTable();
    }

    @FXML
    void editClientButtonAction(ActionEvent event) throws Exception{
        if (tableViewClients.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        EditClientController.editClient = tableViewClients.getSelectionModel().getSelectedItem();
        toScene("route/edit_route.fxml", "List Routes");
    }

    @FXML
    void idInputTextFieldAction(InputMethodEvent event) {

    }

    private void refreshTable() {
        tableClients.setAll(dao.getList());
        tableViewClients.setItems(tableClients);
    }

    @FXML
    void showTicketsMouseClicked(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("../../../../../fxml/ticket/list_tickets.fxml").openStream());
        TicketListController fooController = (TicketListController) fxmlLoader.getController();
        fooController.setClient(tableViewClients.getSelectionModel().getSelectedItem());
        toScene("ticket/list_tickets.fxml", "List Tickets of client");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableClientColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableClientColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableClientsColumnMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tableClientsColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        refreshTable();

    }

}

