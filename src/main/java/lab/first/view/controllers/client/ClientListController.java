package lab.first.view.controllers.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import lab.first.model.Client;

public class ClientListController implements Initializable {

    public static ObservableList<Client> tableClients  = FXCollections.observableArrayList();

    public ObservableList<Client> getTableClients() {
        return tableClients;
    }

    public void setTableRoutes(ObservableList<Client> tableClients) {
        this.tableClients = tableClients;
    }

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
        toScene("client/new_client.fxml", "New Client", event);
    }

    @FXML
    void deleteClientButtonAction(ActionEvent event) {
        UUID id = null;
        if (tableViewClients.getSelectionModel().getSelectedItem() != null) {
            id = (UUID) tableViewClients.getSelectionModel().getSelectedItem().getId(); // по id будем искать в листе редактируемый маршрут
        } else {
            return;
        }
        for (int i = 0; i < tableClients.size(); i++) {
            if (tableClients.get(i).getId() == id) {
                tableClients.remove(i);
            }
        }
        refreshTable();
    }

    @FXML
    void editClientButtonAction(ActionEvent event) throws IOException {
        UUID id = null;
        if(tableViewClients.getSelectionModel().getSelectedItem() != null) {
            id = (UUID) tableViewClients.getSelectionModel().getSelectedItem().getId(); // по id будем искать в листе редактируемого клиента
        } else {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../fxml/client/edit_client.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        EditClientController editController = loader.getController(); //получаем контроллер для второй формы
        Client client = null;
        for (int i = 0; i < tableClients.size(); i++) {
            if (tableClients.get(i).getId() == id) {
                client = tableClients.get(i);
                break;
            }
        }
        editController.writeInFields(client);
        editController.setEditClient(client);
        stage.setTitle("Edit Client");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void idInputTextFieldAction(InputMethodEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableClientColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableClientColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableClientsColumnMiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        tableClientsColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        tableViewClients.setItems(tableClients);
    }

    private void refreshTable() {
        tableViewClients.setItems(tableClients);
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

}

