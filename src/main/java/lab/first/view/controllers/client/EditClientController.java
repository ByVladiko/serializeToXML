package lab.first.view.controllers.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lab.first.model.Client;
import lab.first.view.controllers.MainControl;

public class EditClientController extends MainControl {

    static Client editClient;

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
        editClient.setFirstName(firstNameTextField.getText());
        editClient.setMiddleName(middleNameField.getText());
        editClient.setLastName(lastNameTextField.getText());
        ClientListController.dao.add(editClient);
        toScene("client/list_clients.fxml", "List Clients", event);
    }

    @FXML
    void initialize() {
        firstNameTextField.setText(editClient.getFirstName());
        middleNameField.setText(editClient.getMiddleName());
        lastNameTextField.setText(editClient.getLastName());
    }
}
