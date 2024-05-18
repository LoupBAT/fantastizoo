package fr.g4zoo.fantastizoo.controllers;

import fr.g4zoo.fantastizoo.models.Zoo;
import fr.g4zoo.fantastizoo.models.ZooMaster;
import fr.g4zoo.fantastizoo.models.creatures.Phoenix;
import fr.g4zoo.fantastizoo.models.enclosures.Aviary;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

public class ZooAppController {

    @FXML
    private ListView<String> enclosureListView;

    @FXML
    private ListView<String> creatureListView;

    @FXML
    private Label creatureCountLabel;

    @FXML
    private Text enclosure_area;

    @FXML
    private Text enclosure_capacity;

    @FXML
    private ProgressBar enclosure_cleanliness_bar;

    @FXML
    private Text enclosure_cleanliness;

    private Zoo zoo;

    public void initialize() {
        //CECI EST UN TEST POUR LES ENCLOS
        zoo = new Zoo("Fantastizoo", new ZooMaster());


        Aviary aviary = new Aviary("Phénix 1", 500.0, 20.0);

        Phoenix phoenixMale = new Phoenix("Ember");
        phoenixMale.setGender('m');
        phoenixMale.setAge(15);
        phoenixMale.setWeight(10.0);
        phoenixMale.setHeight(2.0);

        Phoenix phoenixFemale = new Phoenix("Blaze");
        phoenixFemale.setGender('f');
        phoenixFemale.setAge(12);
        phoenixFemale.setWeight(8.0);
        phoenixFemale.setHeight(1.8);

        Phoenix phoenixFemale2 = new Phoenix("Rode");
        phoenixFemale.setGender('f');
        phoenixFemale.setAge(16);
        phoenixFemale.setWeight(18.0);
        phoenixFemale.setHeight(10.8);

        aviary.addCreature(phoenixMale);
        aviary.addCreature(phoenixFemale);
        aviary.addCreature(phoenixFemale2);

        Aviary secondAviary = new Aviary("Phénix 2", 800.0, 30.0);

        Phoenix newPhoenixMale = new Phoenix("Fawkes");
        newPhoenixMale.setGender('m');
        newPhoenixMale.setAge(20);
        newPhoenixMale.setWeight(12.0);
        newPhoenixMale.setHeight(2.2);

        Phoenix newPhoenixFemale = new Phoenix("Pyro");
        newPhoenixFemale.setGender('f');
        newPhoenixFemale.setAge(18);
        newPhoenixFemale.setWeight(9.0);
        newPhoenixFemale.setHeight(2.0);

        secondAviary.addCreature(newPhoenixMale);
        secondAviary.addCreature(newPhoenixFemale);

        zoo.addEnclosure(aviary);
        zoo.addEnclosure(secondAviary);
        //FIN DU TEST POUR LES ENCLOS

        updateUI();

        enclosureListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Enclosure selectedEnclosure = zoo.getEnclosureByName(newValue);
                if (selectedEnclosure != null) {
                    enclosure_area.setText(selectedEnclosure.getArea() + " m2");
                    enclosure_capacity.setText(selectedEnclosure.getCreatureNumber() + "/" + selectedEnclosure.getMaxCapacity());
                    enclosure_cleanliness_bar.setProgress((double) selectedEnclosure.getCleanliness() / 100.0);
                    enclosure_cleanliness.setText(selectedEnclosure.getCleanliness() > 75 ? "Propre" : selectedEnclosure.getCleanliness() > 50 ? "Correct" : "Sale");
                    updateCreatureListView((Aviary) selectedEnclosure);
                }
            }
        });
    }

    private void updateUI() {
        if (zoo != null) {
            int totalCreatureCount = 0; // Initialisation du nombre total de créatures à 0

            enclosureListView.getItems().clear();
            for (Enclosure enclosure : zoo.getAllEnclosures()) {
                enclosureListView.getItems().add(enclosure.getName());
                totalCreatureCount += enclosure.getCreatureNumber(); // Ajouter le nombre de créatures de l'enclos au total
            }

            creatureCountLabel.setText("" + totalCreatureCount); // Mettre à jour le label avec le nombre total de créatures
        }
    }


    private void updateCreatureListView(Aviary enclosure) {
        creatureListView.getItems().clear();
        enclosure.getCreatures().forEach(creature -> {
            String creatureInfo = String.format("Nom: %s, Genre: %c, Âge: %d, Poids: %.2f kg, Taille: %.2f m, Satiété: %d, Vie: %d, Est endormi: %s",
                    creature.getName(), creature.getGender(), creature.getAge(), creature.getWeight(), creature.getHeight(),
                    creature.getSatiety(), creature.getHealth(), creature.isAsleep() ? "Oui" : "Non");
            creatureListView.getItems().add(creatureInfo);
        });
    }

}
