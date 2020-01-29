package lab.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab.first.model.Airship;
import lab.first.serialize.AirshipXmlImpl;
import lab.first.view.controllers.Util;

import java.util.UUID;

public class MainApp extends Application {

    public static void main(String[] args) {
        AirshipXmlImpl xml = new AirshipXmlImpl();
        xml.save(new Airship(UUID.fromString("88be61ce-086c-43bc-9eb8-b96df5894ae9"), "Aerobus", 100));
        xml.save(new Airship(UUID.fromString("40a81aa3-d160-4d6a-8f28-ecdd2b601630"), "Aeroflot", 150));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/route/list_routes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Util.mainStage = primaryStage;
        primaryStage.setTitle("List Routes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}