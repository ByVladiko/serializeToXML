package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public Stage MainStage;

    public static void main(String[] args) {
        System.out.println();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("model/list_routes.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}