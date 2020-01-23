package lab.first.view.controllers.route;

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
import lab.first.dao.RouteDAOImpl;
import lab.first.model.Route;
import lab.first.view.ConverterToFX;

public class RouteListController implements Initializable {

    private ConverterToFX converter = new ConverterToFX();

    private ObservableList<Route> tableRoutes  = FXCollections.observableArrayList();

    protected static RouteDAOImpl dao = new RouteDAOImpl();;

    public ObservableList<Route> getTableRoutes() {
        return tableRoutes;
    }

    public void setTableRoutes(ObservableList<Route> tableRoutes) {
        this.tableRoutes = tableRoutes;
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
    private TextField idTextField;

    @FXML
    private TableView<Route> tableViewRoutes;

    @FXML
    private TableColumn<Route, Integer> tableRoutesColumnId;

    @FXML
    private TableColumn<Route, String> tableRoutesColumnFrom;

    @FXML
    private TableColumn<Route, String> tableRoutesColumnTo;

    @FXML
    private Button addRouteButton;

    @FXML
    private Button editRouteButton;

    @FXML
    private Button deleteRouteButton;

    @FXML
    private Button mainRoutesButton;

    @FXML
    private Button mainTicketsButton;

    @FXML
    private Button mainClientsButton;

    @FXML
    public void addRouteButtonAction(ActionEvent event) throws Exception {
        toScene("route/new_route.fxml", "New Route", event);
//        ((Node)(event.getSource())).getScene().getWindow().hide();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("new_route.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
    }

    @FXML
    public void deleteRouteButtonAction(ActionEvent event) {
        Route route = null;
        if (tableViewRoutes.getSelectionModel().getSelectedItem() != null) {
            route = new Route(tableViewRoutes.getSelectionModel().getSelectedItem().getId(), tableViewRoutes.getSelectionModel().getSelectedItem().getStartPoint(), tableViewRoutes.getSelectionModel().getSelectedItem().getEndPoint()); // по id будем искать в листе редактируемый маршрут
        } else {
            return;
        }
            dao.remove(route);
            refreshTable();
    }

    @FXML
    void editRouteButtonAction(ActionEvent event) throws Exception {
        UUID id = null;
        if(tableViewRoutes.getSelectionModel().getSelectedItem() != null) {
            id = (UUID) tableViewRoutes.getSelectionModel().getSelectedItem().getId(); // по id будем искать в листе редактируемый маршрут
        } else {
            return;
        }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../fxml/route/edit_route.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            EditRouteController editController = loader.getController(); //получаем контроллер для второй формы
            Route route = null;
            for (int i = 0; i < tableRoutes.size(); i++) {
                if (tableRoutes.get(i).getId() == id) {
                    route = tableRoutes.get(i);
                    break;
                }
            }
            editController.writeInFields(route);
            editController.setEditRoute(route);
            stage.setTitle("Edit Route");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void idInputTextFieldAction(InputMethodEvent event) {
//        if(!idTextField.getText().isEmpty()) {
//           tableRoutes.stream().filter(route->((route.getId().toString().indexOf(idTextField.getText()) > 0)? true : false));
//        }
//        else tableViewRoutes.setItems(tableRoutes);
    }

    private void refreshTable() {
        tableRoutes.setAll(dao.getList());
        tableViewRoutes.setItems(tableRoutes);
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

    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableRoutesColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableRoutesColumnFrom.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        tableRoutesColumnTo.setCellValueFactory(new PropertyValueFactory<>("endPoint"));

        refreshTable();
    }
}

