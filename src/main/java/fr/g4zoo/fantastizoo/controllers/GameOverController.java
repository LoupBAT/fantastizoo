package fr.g4zoo.fantastizoo.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameOverController {

    @FXML
    private Text time;

    public void setTime(String timeValue) {
        time.setText(timeValue);
    }

}
