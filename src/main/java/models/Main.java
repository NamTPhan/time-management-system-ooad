package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WINDOW_HEIGHT = 1280;
    private static final int WINDOW_WIDTH = 800;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../views/session.fxml"));

        primaryStage.setTitle("Timemanagement");
        primaryStage.setScene(new Scene(root, WINDOW_HEIGHT, WINDOW_WIDTH));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
