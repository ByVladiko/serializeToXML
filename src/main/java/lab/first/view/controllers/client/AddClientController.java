package lab.first.view.controllers.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lab.first.dao.ClientDAOImpl;
import lab.first.model.Client;
import lab.first.view.controllers.MainControl;

public class AddClientController extends MainControl {

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
    void saveClientButtonAction(ActionEvent event) throws Exception {
        String regex = "^[a-zA-Z0-9А-Яа-я._-]{3,}$";
        if(!firstNameTextField.getText().matches(regex) || !middleNameField.getText().matches(regex)) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Attention");
            a.setHeaderText("Ops!");
            String version = System.getProperty("java.version");
            String content = String.format("Incorrect input", version);
            a.setContentText(content);
            a.showAndWait();
            return;
        }
        ClientDAOImpl.getInstance().add(new Client(firstNameTextField.getText(), middleNameField.getText(), lastNameTextField.getText()));
        toScene("client/list_clients.fxml", "List Clients", event);
    }

    @FXML
    void initialize() {
    }
}
