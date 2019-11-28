package lab.first.controllers;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import lab.first.model.Route;

public class RouteListController implements Initializable {

    private ObservableList<Route> tableRoutes = FXCollections.observableArrayList();
    public static Stage stage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

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
    void backButtonAction(ActionEvent event) {

    }

    @FXML
    void addRouteButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new_route.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void deleteRouteButtonAction(ActionEvent event) {
        UUID id = (UUID) tableViewRoutes.getSelectionModel().getSelectedItem().getId(); // по id будем искать в листе удаляемый маршрут

    }

    @FXML
    void editRouteButtonAction(ActionEvent event) throws IOException {
        UUID id = (UUID) tableViewRoutes.getSelectionModel().getSelectedItem().getId(); // по id будем искать в листе редактируемый маршрут

        FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_route.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
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

