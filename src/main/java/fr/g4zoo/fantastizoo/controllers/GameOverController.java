package fr.g4zoo.fantastizoo.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameOverController {

    @FXML
    private Text time;

    /**
     * Sets time for the game over view.
     *
     * @param timeValue the time value
     */
    public void setTime(String timeValue) {
        time.setText(timeValue);
    }

}
