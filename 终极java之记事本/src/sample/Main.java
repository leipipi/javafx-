package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    public static Map<String, Object> controllers = new HashMap<String, Object>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("无标题—记事本");
        primaryStage.setScene(new Scene(root, 679, 619));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
