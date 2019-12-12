package lab.first.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.stream.Collectors;

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
import lab.first.model.Route;

public class RouteListController implements Initializable {

    private ObservableList<Route> tableRoutes  = FXCollections.observableArrayList();

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
    private Button mainInfoButton;

    @FXML
    public void addRouteButtonAction(ActionEvent event) throws Exception {
        toScene("../../../new_route.fxml", "New Route", event);
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
        UUID id = (UUID) tableViewRoutes.getSelectionModel().getSelectedItem().getId(); // по id будем искать в листе удаляемый маршрут

    }

    @FXML
    void editRouteButtonAction(ActionEvent event) throws Exception {
//        UUID id = (UUID) tableViewRoutes.getSelectionModel().getSelectedItem().getId(); // по id будем искать в листе редактируемый маршрут
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../edit_route.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        EditRouteController editController = loader.getController(); //получаем контроллер для второй формы
        Route route = new Route("Mexico", "Brazil");
        editController.writeInFields(route);
        editController.setEditRoute(route);
        stage.setTitle("Edit Route");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void mainRoutesButtonAction(ActionEvent event) {
        refreshTable();
    }

    private void toScene(String path, String title, ActionEvent event) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();

        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root, width, height);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void mainTicketsButtonAction(ActionEvent event) {

    }

    @FXML
    void mainInfoButtonAction(ActionEvent event) {

    }

    @FXML
    void idInputTextFieldAction(InputMethodEvent event) {
//        if(!idTextField.getText().isEmpty()) {
//           tableRoutes.stream().filter(route->((route.getId().toString().indexOf(idTextField.getText()) > 0)? true : false));
//        }
//        else tableViewRoutes.setItems(tableRoutes);
    }

    private void refreshTable() {
        tableViewRoutes.setItems(tableRoutes);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableRoutes.add(new Route("L.A.", "Moscow"));
        tableRoutes.add(new Route("Kostroma", "Saratov"));
        tableRoutes.add(new Route("Tolyatti", "Chelyabinsk"));

        tableRoutesColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableRoutesColumnFrom.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        tableRoutesColumnTo.setCellValueFactory(new PropertyValueFactory<>("endPoint"));

        tableViewRoutes.setItems(tableRoutes);
    }
}

