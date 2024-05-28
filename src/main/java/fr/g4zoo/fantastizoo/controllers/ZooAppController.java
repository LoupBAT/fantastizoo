package fr.g4zoo.fantastizoo.controllers;

import fr.g4zoo.fantastizoo.models.Zoo;
import fr.g4zoo.fantastizoo.models.ZooMaster;
import fr.g4zoo.fantastizoo.models.creatures.Creature;
import fr.g4zoo.fantastizoo.models.creatures.Phoenix;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Flyer;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Runner;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Aviary;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @FXML
    private Label zooName;

    @FXML
    public ChoiceBox<String> enclosureListTransfer;

    private Zoo zoo;

    private Enclosure selectedEnclosure;

    private Map<String, Integer> creatureIdMap = new HashMap<>();

    private Creature selectedCreature;


    public Zoo getZoo() {
        return zoo;
    }

    public Enclosure getSelectedEnclosure() {
        return selectedEnclosure;
    }

    public Creature getSelectedCreature() {
        return selectedCreature;
    }




    public void initialize(ZooMaster master, String zooName) throws IOException {

        // TEST POUR LES ENCLOS

        zoo = new Zoo(zooName, master);
        this.zooName.setText(zooName);

        Aviary aviary = new Aviary("Phénix 1", 500.0, 20.0);

        Phoenix phoenixMale = new Phoenix("Ember", aviary, 'm', 15, 10.0, 2.0);
        Phoenix phoenixFemale = new Phoenix("Blaze", aviary, 'f', 12, 8.0, 1.8);
        Phoenix phoenixFemale2 = new Phoenix("Rode", aviary, 'f', 16, 18.0, 10.8);

        Aviary secondAviary = new Aviary("Phénix 2", 800.0, 30.0);

        Phoenix newPhoenixMale = new Phoenix("Fawkes", secondAviary, 'm', 20, 12.0, 2.2);
        Phoenix newPhoenixFemale = new Phoenix("Pyro", secondAviary, 'f', 18, 9.0, 2.0);

        System.out.println(newPhoenixFemale.getId());
        System.out.println(newPhoenixMale.getId());
        System.out.println(phoenixFemale.getId());

        zoo.addEnclosure(aviary);
        zoo.addEnclosure(secondAviary);

        // FIN DU TEST POUR LES ENCLOS

        updateUI();

        enclosureListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedEnclosure = zoo.getEnclosureByName(newValue);
                if (selectedEnclosure != null) {
                    enclosure_area.setText(selectedEnclosure.getArea() + " m2");
                    enclosure_capacity.setText(selectedEnclosure.getCreatureNumber() + "/" + selectedEnclosure.getMaxCapacity());
                    enclosure_cleanliness_bar.setProgress((double) selectedEnclosure.getCleanliness() / 100.0);
                    enclosure_cleanliness.setText(selectedEnclosure.getCleanliness() > 75 ? "Propre" : selectedEnclosure.getCleanliness() > 50 ? "Correct" : "Sale");
                    updateCreatureListView((Aviary) selectedEnclosure);
                }
            }
        });


        creatureListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedCreatureId = creatureIdMap.get(newValue);
                selectedCreature = selectedEnclosure.getCreatureById(selectedCreatureId);
                updateEnclosureListTransfer();
            }
        });


    }

    private void updateUI() {
        if (zoo != null) {
            int totalCreatureCount = 0;

            enclosureListView.getItems().clear();
            for (Enclosure enclosure : zoo.getAllEnclosures()) {
                enclosureListView.getItems().add(enclosure.getName());
                totalCreatureCount += enclosure.getCreatureNumber();
            }

            creatureCountLabel.setText("" + totalCreatureCount);
        }
    }


    private void updateCreatureListView(Enclosure enclosure) {
        creatureListView.getItems().clear();
        creatureIdMap.clear();
        enclosure.getCreatures().forEach(creature -> {
            String creatureInfo = String.format("Nom: %s, Genre: %c, Âge: %d, Poids: %.2f kg, Taille: %.2f m, Satiété: %d, Vie: %d, Est endormi: %s",
                    creature.getName(), creature.getGender(), creature.getAge(), creature.getWeight(), creature.getHeight(),
                    creature.getSatiety(), creature.getHealth(), creature.isAsleep() ? "Oui" : "Non");
            creatureListView.getItems().add(creatureInfo);
            creatureIdMap.put(creatureInfo, creature.getId());
        });
    }

    void updateEnclosureListTransfer() {
        if (this.getZoo() != null) {
            Zoo zoo = this.getZoo();

            enclosureListTransfer.getItems().clear();

            for (Enclosure enclosure : zoo.getAllEnclosures()) {
                if (!enclosure.equals(this.getSelectedEnclosure())) {
                    enclosureListTransfer.getItems().add(enclosure.getName());
                }
            }
        }
    }

    @FXML
    public void onClickHeal(ActionEvent event) {
        this.getSelectedCreature().healing(40);
    }

    @FXML
    public void onClickTrain(ActionEvent event) {
        if (this.getSelectedCreature() instanceof Flyer) {
            ((Flyer) this.getSelectedCreature()).fly();
        } else if (this.getSelectedCreature() instanceof Swimmer ) {
            ((Swimmer) this.getSelectedCreature()).swim();
        } else {
            ((Runner) this.getSelectedCreature()).run();
        }
    }



    @FXML
    public void onClickTransfer(ActionEvent actionEvent) {
        getSelectedEnclosure().removeCreature(getSelectedCreature());
        zoo.getEnclosureByName(enclosureListTransfer.getValue()).addCreature(getSelectedCreature());
        updateCreatureListView(getSelectedEnclosure());
    }


}
