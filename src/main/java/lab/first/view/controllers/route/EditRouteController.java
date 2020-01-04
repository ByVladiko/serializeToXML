package lab.first.view.controllers.route;

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
    private Button clientsMainButton;

    @FXML
    void saveRouteButtonAction(ActionEvent event) throws  Exception {
        editRoute.setStartPoint(fromTextField.getText());
        editRoute.setEndPoint(toTextField.getText());
        for (int i = 0; i < RouteListController.tableRoutes.size(); i++) {
            if (RouteListController.tableRoutes.get(i).getId() == editRoute.getId()) {
                RouteListController.tableRoutes.set(i, editRoute);
            }
            break;
        }
        toScene("route/list_routes.fxml", "List Routes", event);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void writeInFields(Route route) {
        fromTextField.setText(route.getStartPoint());
        toTextField.setText(route.getEndPoint());
    }
}
