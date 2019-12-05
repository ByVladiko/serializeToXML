package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab.first.controllers.RouteListController;

public class MainApp extends Application {

    public static void main(String[] args) {
        System.out.println();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("model/list_routes.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("model/list_routes.fxml"));
        //getClass().getResource("../../list_routes.fxml")
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}