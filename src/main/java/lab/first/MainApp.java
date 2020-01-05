package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab.first.model.Client;
import lab.first.view.controllers.client.ClientListController;
import lab.first.view.controllers.route.RouteListController;
import lab.first.model.Route;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        RouteListController.tableRoutes.add(new Route("L.A.", "Moscow"));
        RouteListController.tableRoutes.add(new Route("Kostroma", "Saratov"));
        RouteListController.tableRoutes.add(new Route("Tolyatti", "Chelyabinsk"));

        ClientListController.tableClients.add(new Client("Arkadii", "Eremeev", "Anatolevich"));
        ClientListController.tableClients.add(new Client("Viktor", "Prokofev", "Gennadievich"));
        ClientListController.tableClients.add(new Client("Leonid", "Gerasimov", "Sergeevich"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/route/list_routes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("List Routes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}