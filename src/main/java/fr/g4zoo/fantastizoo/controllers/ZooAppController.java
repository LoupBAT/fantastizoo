package fr.g4zoo.fantastizoo.controllers;

import fr.g4zoo.fantastizoo.models.Console;
import fr.g4zoo.fantastizoo.models.Zoo;
import fr.g4zoo.fantastizoo.models.ZooMaster;
import fr.g4zoo.fantastizoo.models.Watch;
import fr.g4zoo.fantastizoo.models.creatures.Creature;
import fr.g4zoo.fantastizoo.models.creatures.Phoenix;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Flyer;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Runner;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Aviary;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.scene.image.ImageView;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Text txt_enclosureName;


    @FXML
    private ProgressBar creature_life_bar;

    @FXML
    private Text txt_life;

    @FXML
    private ProgressBar creature_satiety_bar;

    @FXML
    private Text txt_satiety;

    @FXML
    private Text txt_creatureName;

    @FXML
    private Text txt_creatureAge;

    @FXML
    private Text txt_creatureHeight;

    @FXML
    private Text txt_creatureWeight;

    @FXML
    private Text txt_creatureGender;

    @FXML
    private Text txt_creatureSleep;

    @FXML
    private TextArea showConsol;

    @FXML
    private Text enclosure_cleanliness;

    @FXML
    private Label zooName;

    @FXML
    private Label zooMasterName;

    @FXML
    private Label zooMasterHp;

    @FXML
    private Text txt_time;

    @FXML
    public ChoiceBox<String> enclosureListTransfer;

    private Zoo zoo;

    private Enclosure selectedEnclosure;

    private Map<String, Integer> creatureIdMap = new HashMap<>();

    private Creature selectedCreature;

    private Watch watch;

    private Thread watchThread;

    private Thread timeUpdateThread;

    public Zoo getZoo() {
        return zoo;
    }

    public Enclosure getSelectedEnclosure() {
        return selectedEnclosure;
    }

    public Creature getSelectedCreature() {
        return selectedCreature;
    }

    Thread welcomeThread = new Thread(() -> {
        try {
            Thread.sleep(1000);
            System.out.println("Bienvenue sur Fantastizoo !");
            Thread.sleep(1000);
            System.out.println("Fantastizoo est une application où vous gérez \nvotre propre zoo fantastique rempli de créatures \nincroyables.");
            Thread.sleep(2000);
            System.out.println("Votre objectif est de collecter un maximum de \ncréatures différentes, de les nourrir, de les \nentraîner et de veiller à leur bien-être.");
            Thread.sleep(2000);
            System.out.println("Amusez-vous à découvrir et à interagir avec les \ndifférentes espèces de créatures et à construire le \nzoo de vos rêves !");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    public void initialize(ZooMaster master, String zooName) throws IOException {
        welcomeThread.start();

        watch = new Watch();
        watchThread = new Thread(watch);
        watchThread.setDaemon(true);
        watchThread.start();

        timeUpdateThread = new Thread(() -> {
            try {
                while (true) {
                    Platform.runLater(() -> txt_time.setText(watch.getTime()));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timeUpdateThread.setDaemon(true);
        timeUpdateThread.start();

        // Display console on screen
        PrintStream ps = new PrintStream(new Console(showConsol), true, "UTF-8");
        System.setOut(ps);
        System.setErr(ps);

        zoo = new Zoo(zooName, master);
        this.zooName.setText(zooName);
        this.zooMasterName.setText(master.getName());
        this.zooMasterHp.setText(master.getHp() + "");

        Aviary aviary = new Aviary("Phénix 1", 500.0, 20.0);

        Phoenix phoenixMale = new Phoenix("Ember", aviary, 'm', 15, 10.0, 2.0);
        Phoenix phoenixFemale = new Phoenix("Blaze", aviary, 'f', 12, 8.0, 1.8);
        Phoenix phoenixFemale2 = new Phoenix("Rode", aviary, 'f', 16, 18.0, 10.8);

        Aviary secondAviary = new Aviary("Phénix 2", 800.0, 30.0);

        Phoenix newPhoenixMale = new Phoenix("Fawkes", secondAviary, 'm', 20, 12.0, 2.2);
        Phoenix newPhoenixFemale = new Phoenix("Pyro", secondAviary, 'f', 18, 9.0, 2.0);

        zoo.addEnclosure(aviary);
        zoo.addEnclosure(secondAviary);

        updateUI();

        enclosureListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedEnclosure = zoo.getEnclosureByName(newValue);
                if (selectedEnclosure != null) {
                    enclosure_area.setText(selectedEnclosure.getArea() + " m2");
                    enclosure_capacity.setText(selectedEnclosure.getCreatureNumber() + "/" + selectedEnclosure.getMaxCapacity());
                    enclosure_cleanliness_bar.setProgress((double) selectedEnclosure.getCleanliness() / 100.0);
                    enclosure_cleanliness.setText(selectedEnclosure.getCleanliness() > 75 ? "Propre" : selectedEnclosure.getCleanliness() > 50 ? "Correct" : "Sale");
                    txt_enclosureName.setText(selectedEnclosure.getName());
                    updateCreatureListView(selectedEnclosure);
                }
            }
        });


        creatureListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedCreatureId = creatureIdMap.get(newValue);
                selectedCreature = selectedEnclosure.getCreatureById(selectedCreatureId);
                updateEnclosureListTransfer();

                creature_life_bar.setProgress((double) selectedCreature.getHealth() / 100.0);
                creature_satiety_bar.setProgress((double) selectedCreature.getSatiety() / 100.0);

                txt_life.setText(""+selectedCreature.getHealth());
                txt_satiety.setText(""+selectedCreature.getSatiety());
                txt_creatureName.setText(selectedCreature.getName());
                txt_creatureAge.setText(selectedCreature.getAge()+" ans");
                txt_creatureHeight.setText(selectedCreature.getHeight()+" m");
                txt_creatureWeight.setText(selectedCreature.getWeight()+" kg");
                if (selectedCreature.getGender() == 'm') {
                    txt_creatureGender.setText("Male");
                } else {
                    txt_creatureGender.setText("Femelle");
                }
                if (selectedCreature.isAsleep()) {
                    txt_creatureSleep.setText("Dort");
                } else {
                    txt_creatureSleep.setText("Eveillé");
                }
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
            String creatureInfo = String.format("Nom: %s",
                    creature.getName());
            creatureListView.getItems().add(creatureInfo);
            creatureIdMap.put(creatureInfo, creature.getId());
        });
    }

    private void updateSelectedEnclosure(String enclosureName) {
        if (enclosureName != null) {
            selectedEnclosure = zoo.getEnclosureByName(enclosureName);
            if (selectedEnclosure != null) {
                enclosure_area.setText(selectedEnclosure.getArea() + " m2");
                enclosure_capacity.setText(selectedEnclosure.getCreatureNumber() + "/" + selectedEnclosure.getMaxCapacity());
                enclosure_cleanliness_bar.setProgress((double) selectedEnclosure.getCleanliness() / 100.0);
                enclosure_cleanliness.setText(selectedEnclosure.getCleanliness() > 75 ? "Propre" : selectedEnclosure.getCleanliness() > 50 ? "Correct" : "Sale");
                updateCreatureListView((Aviary) selectedEnclosure);
            }
        }
    }

    private void updateSelectedCreature(String creatureInfo) {
        if (creatureInfo != null) {
            int selectedCreatureId = creatureIdMap.get(creatureInfo);
            selectedCreature = selectedEnclosure.getCreatureById(selectedCreatureId);
            updateEnclosureListTransfer();

            creature_life_bar.setProgress((double) selectedCreature.getHealth() / 100.0);
            creature_satiety_bar.setProgress((double) selectedCreature.getSatiety() / 100.0);

            txt_life.setText("" + selectedCreature.getHealth());
            txt_satiety.setText("" + selectedCreature.getSatiety());
            txt_creatureName.setText(selectedCreature.getName());
            txt_creatureAge.setText(selectedCreature.getAge() + " ans");
            txt_creatureHeight.setText(selectedCreature.getHeight() + " m");
            txt_creatureWeight.setText(selectedCreature.getWeight() + " kg");
            txt_creatureGender.setText(selectedCreature.getGender() == 'm' ? "Male" : "Femelle");
            txt_creatureSleep.setText(selectedCreature.isAsleep() ? "Dort" : "Eveillé");
        }
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
        System.out.println(getSelectedCreature().getName() + " a été soigné.");
        updateSelectedCreature(creatureListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onClickTrain(ActionEvent event) {
        if (this.getSelectedCreature() instanceof Flyer) {
            ((Flyer) this.getSelectedCreature()).fly();
            reduceWeight(0.5);
        } else if (this.getSelectedCreature() instanceof Swimmer ) {
            ((Swimmer) this.getSelectedCreature()).swim();
            reduceWeight(0.5);
        } else if (this.getSelectedCreature() instanceof Runner ) {
            ((Runner) this.getSelectedCreature()).run();
            reduceWeight(0.5);
        } else {
            reduceWeight(0.3);
            System.out.println(getSelectedCreature().getName() + " s'entraine.");
        }
        updateSelectedCreature(creatureListView.getSelectionModel().getSelectedItem());
    }

    private void reduceWeight(double amount) {
        double currentWeight = this.getSelectedCreature().getWeight();
        double newWeight = currentWeight - amount;
        if (newWeight < 0) {
            newWeight = 0;
        }
        this.getSelectedCreature().setWeight(newWeight);
    }


    @FXML
    public void onClickTransfer(ActionEvent actionEvent) {
        Enclosure targetEnclosure = zoo.getEnclosureByName(enclosureListTransfer.getValue());
        if (targetEnclosure != null) {
            Creature transferredCreature = getSelectedCreature();
            getSelectedEnclosure().removeCreature(transferredCreature);
            targetEnclosure.addCreature(transferredCreature);
            updateCreatureListView(getSelectedEnclosure());

            String message = transferredCreature.getName() + " a été transféré dans l'enclos " + targetEnclosure.getName();
            System.out.println(message);
        }
    }


    @FXML
    public void onClickClean(ActionEvent actionEvent) {
        int cleanliness = getSelectedEnclosure().getCleanliness();
        if (cleanliness == 100) {
            System.out.println("L'enclos est déjà propre.");
        } else {
            getSelectedEnclosure().getCleaned();
            updateSelectedEnclosure(getSelectedEnclosure().getName());
        }
    }

    @FXML
    public void onClickFeed(ActionEvent actionEvent) {
        getSelectedEnclosure().feedCreatures();
        updateSelectedCreature(creatureListView.getSelectionModel().getSelectedItem());
    }

}
