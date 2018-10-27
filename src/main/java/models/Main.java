package models;

import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WINDOW_HEIGHT = 1280, WINDOW_WIDTH = 800;
    private TimerController controller;
    private final int DEFAULT_SETTINGS_ID = 1;
    private GenericDAO<Settings> settingsDao = new SettingsDaoImplementation();
    private Settings settings;

    @Override
    public void start(Stage primaryStage) throws Exception {

        if (settingsDao.getAll().size() == 0){
            settings = new Settings();
            settingsDao.save(settings);
        }
        
        settings = settingsDao.getByIndex(DEFAULT_SETTINGS_ID);
        System.out.println(settings.getSettingsId() + " " + settings.getLengthLongBreak() + " " + settings.getLengthShortBreak());

        switch (settings.getTimerType()) {
            case 1: controller = new HourTimerController(); break;
            case 2: controller = new TenMinuteTimerController(); break;
            default: controller = new PomodoroTimerController(); break;
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
