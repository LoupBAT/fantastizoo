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

    @FXML
    void onClickCancel(ActionEvent event) {
        men.setSelected(false);
        women.setSelected(false);
        Age.clear();
        name.clear();
        zoo.clear();
    }

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


    @FXML
    void onClickSave(ActionEvent event) throws IOException {
        String nameValue = name.getText();
        int ageValue = Integer.parseInt(Age.getText());
        Character genderValue = men.isSelected() ? 'h' : (women.isSelected() ? 'f' : null);
        int hpValue = 3;
        int actionValue = 5;
        String zooValue = zoo.getText();

        if (nameValue != null && !nameValue.isEmpty() && ageValue != 0 && zooValue != null && !zooValue.isEmpty()) {
            ZooMaster master = new ZooMaster();
            master.setName(nameValue);
            master.setAge(ageValue);
            master.setGender(genderValue);
            master.setHp(hpValue);
            master.setAction(actionValue);

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
