/*
 * Class set stage for switching between screens
 */
package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Nam
 */
public class SetStage {

    //Public constructor
    public SetStage(Stage stage, String fxmlFile)
            throws Exception {
        //Here we have obtained the stage.

        /*Create the root container (the upper structure in an
            fxml file such as AnchorPane) of the first screen (aka "scene").
            The root contains all other UI structures as defined in fxml.*/
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

        //Create a new scene (screen) out of this.
        Scene scene = new Scene(root);

        //Set this new scene on stage and show it to the user.
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
