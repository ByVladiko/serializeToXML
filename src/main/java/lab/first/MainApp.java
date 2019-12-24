package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab.first.controllers.RouteListController;
import lab.first.model.Route;

import java.util.ArrayList;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        RouteListController.tableRoutes.add(new Route("L.A.", "Moscow"));
        RouteListController.tableRoutes.add(new Route("Kostroma", "Saratov"));
        RouteListController.tableRoutes.add(new Route("Tolyatti", "Chelyabinsk"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../list_routes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}