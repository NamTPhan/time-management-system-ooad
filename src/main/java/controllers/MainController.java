package controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import models.SetStage;
import models.Task;

public class MainController {

    public void taskOverviewAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new SetStage(stage, "/taskOverview.fxml");
    }

}
