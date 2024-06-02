package fr.g4zoo.fantastizoo.controllers;

import fr.g4zoo.fantastizoo.models.Zoo;
import fr.g4zoo.fantastizoo.models.ZooMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The controller for the first form.
 */
public class ZooMasterController {

    @FXML
    private TextField Age;

    @FXML
    private CheckBox men;

    @FXML
    private TextField name;

    @FXML
    private CheckBox women;

    @FXML
    private TextField zoo;

    /**
     * On click cancel.
     *
     * @param event the event
     */
    @FXML
    void onClickCancel(ActionEvent event) {
        men.setSelected(false);
        women.setSelected(false);
        Age.clear();
        name.clear();
        zoo.clear();
    }

    /**
     * On click check box.
     *
     * @param event the event
     */
    @FXML
    void onClickCheckBox(ActionEvent event) {
        switch (((CheckBox) event.getSource()).getId()) {
            case "women":
                men.setSelected(false);
                break;
            case "men":
                women.setSelected(false);
                break;
        }
    }


    /**
     * On click save.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void onClickSave(ActionEvent event) throws IOException {
        String nameValue = name.getText();
        int ageValue = Integer.parseInt(Age.getText());
        Character genderValue = men.isSelected() ? 'h' : (women.isSelected() ? 'f' : null);
        String zooValue = zoo.getText();

        if (nameValue != null && !nameValue.isEmpty() && ageValue != 0 && zooValue != null && !zooValue.isEmpty()) {
            ZooMaster master = new ZooMaster(nameValue, genderValue, ageValue);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/g4zoo/fantastizoo/fantastizoo.fxml"));
            Parent root = loader.load();

            ZooAppController zooAppController = loader.getController();
            zooAppController.initialize(master, zooValue);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            System.out.println("Veuillez remplir tous les champs !");
        }
    }

}
