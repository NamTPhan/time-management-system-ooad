package models;

import controllers.timers.HourTimerController;
import controllers.timers.PomodoroTimerController;
import controllers.timers.TenMinuteTimerController;
import controllers.timers.TimerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WINDOW_HEIGHT = 1280, WINDOW_WIDTH = 800;
    private TimerController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        int timerId = 1;

        switch (timerId) {
            case 1: controller = new PomodoroTimerController(); break;
            case 2: controller = new HourTimerController(); break;
            case 3: controller = new TenMinuteTimerController(); break;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/session.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

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
