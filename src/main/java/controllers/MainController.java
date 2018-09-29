package controllers;

import javafx.scene.Node;
import javafx.stage.Stage;
import models.SetStage;

public class MainController {

    public void loadSettingsScene(javafx.event.ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/settings.fxml");
    }
}
