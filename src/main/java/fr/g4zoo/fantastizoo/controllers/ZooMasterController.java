package fr.g4zoo.fantastizoo.controllers;

import fr.g4zoo.fantastizoo.models.Zoo;
import fr.g4zoo.fantastizoo.models.ZooMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
    void onClickSave(ActionEvent event) {
        String nameValue = name.getText();
        Integer ageValue = Integer.valueOf(Age.getText());
        Character genderValue = men.isSelected() ? 'h' : (women.isSelected() ? 'f' : null);
        Integer hpValue = 3;
        Integer actionValue = 5;
        String zooValue = zoo.getText();

        if (nameValue != null && !nameValue.isEmpty() && ageValue != null && genderValue != null && zooValue != null && !zooValue.isEmpty( )) {
            ZooMaster master = new ZooMaster();
            master.setName(nameValue);
            master.setAge(ageValue);
            master.setGender(genderValue);
            master.setHp(hpValue);
            master.setAction(actionValue);

            Zoo zoo = new Zoo();
            zoo.setName(zooValue);

            System.out.println("Objet ZooMaster créé avec succès :\n" +
                    "Nom : " + master.getName() + "\n" +
                    "Genre : " + master.getGender() + "\n" +
                    "Âge : " + master.getAge() + "\n" +
                    "HP : " + master.getHp() + "\n" +
                    "Action : " + master.getAction() + "\n" +
                    "Zoo : " + zoo.getName());
        } else {
            System.out.println("Veuillez remplir tous les champs !");
        }
    }

}