package lab.first.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab.first.model.Route;

public class EditRouteController implements Initializable {

    private Route editRoute;

    public void setEditRoute(Route editRoute) {
        this.editRoute = editRoute;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private Button saveRouteButton;

    @FXML
    private Button routesMainButton;

    @FXML
    private Button ticketsMainButton;

    @FXML
    private Button infoMainButton;


    @FXML
    void backButtonAction(ActionEvent event) {

    }

    @FXML
    void saveRouteButtonAction(ActionEvent event) throws  Exception {
        windows("../model/list_routes.fxml", "List Routes", event);
    }

    @FXML
    void routesMainButtonAction(ActionEvent event) throws Exception {
        windows("../model/list_routes.fxml", "List Routes", event);
    }

    @FXML
    void ticketsMainButtonAction(ActionEvent event) {

    }

    @FXML
    void infoMainButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void writeInFields(Route route) {
        fromTextField.setText(route.getStartPoint());
        toTextField.setText(route.getEndPoint());
    }

    private void windows(String path, String title, ActionEvent event) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();

        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
