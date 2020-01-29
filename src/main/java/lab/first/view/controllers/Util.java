package lab.first.view.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Util {

    public static Stage mainStage;

    public static void toScene(String path, String title) throws Exception {

        ClassLoader classLoader = Util.class.getClassLoader();
        Parent root = FXMLLoader.load(classLoader.getResource("fxml/" + path));
        Scene scene = new Scene(root);
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
