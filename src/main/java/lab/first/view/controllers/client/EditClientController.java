package lab.first.view.controllers.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab.first.model.Client;

public class EditClientController {

    private Client editClient;

    public void setEditClient(Client editClient) {
        this.editClient = editClient;
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
    private TextField firstNameTextField;

    @FXML
    private TextField middleNameField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button saveClientButton;

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
    void saveClientButtonAction(ActionEvent event) throws Exception {
        editClient.setFirstName(firstNameTextField.getText());
        editClient.setMiddleName(middleNameField.getText());
        editClient.setLastName(lastNameTextField.getText());
        for (int i = 0; i < ClientListController.tableClients.size(); i++) {
            if (ClientListController.tableClients.get(i).getId() == editClient.getId()) {
                ClientListController.tableClients.set(i, editClient);
            }
            break;
        }
        toScene("client/list_clients.fxml", "List Clients", event);
    }

    @FXML
    void initialize() {

    }

    public void writeInFields(Client client) {
        firstNameTextField.setText(client.getFirstName());
        middleNameField.setText(client.getMiddleName());
        lastNameTextField.setText(client.getLastName());
    }
}
