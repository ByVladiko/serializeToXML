package lab.first.view.controllers.route;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import lab.first.dao.RouteDAOImpl;
import lab.first.model.Route;
import lab.first.view.controllers.MainControl;

import static lab.first.view.controllers.Util.toScene;

public class RouteListController extends MainControl implements Initializable {

    private ObservableList<Route> tableRoutes  = FXCollections.observableArrayList();

    static RouteDAOImpl dao = new RouteDAOImpl();

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
        toScene("route/new_route.fxml", "New Route");
    }

    @FXML
    public void deleteRouteButtonAction(ActionEvent event) {
        if (tableViewRoutes.getSelectionModel().getSelectedItem() == null) {
            return;
        }
            dao.remove(tableViewRoutes.getSelectionModel().getSelectedItem());
            refreshTable();
    }

    @FXML
    void editRouteButtonAction(ActionEvent event) throws Exception {
        if(tableViewRoutes.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        EditRouteController.editRoute = tableViewRoutes.getSelectionModel().getSelectedItem();
        toScene("route/edit_route.fxml", "List Routes");
    }

    @FXML
    void idInputTextFieldAction(InputMethodEvent event) {

    }

    private void refreshTable() {
        tableRoutes.setAll(dao.getList());
        tableViewRoutes.setItems(tableRoutes);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableRoutesColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableRoutesColumnFrom.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        tableRoutesColumnTo.setCellValueFactory(new PropertyValueFactory<>("endPoint"));

        refreshTable();
    }
}

