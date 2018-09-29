package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class SettingsController {

    @FXML
    private ComboBox soundChoice;

    public void setComboBoxData() {
        soundChoice = new ComboBox<String>();
        soundChoice.getItems().addAll("Phone","Alarm Clock");
        soundChoice.setEditable(true);
    }
}
