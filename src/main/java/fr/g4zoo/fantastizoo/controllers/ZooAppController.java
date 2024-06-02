package fr.g4zoo.fantastizoo.controllers;

import fr.g4zoo.fantastizoo.models.Console;
import fr.g4zoo.fantastizoo.models.Zoo;
import fr.g4zoo.fantastizoo.models.ZooMaster;
import fr.g4zoo.fantastizoo.models.Watch;
import fr.g4zoo.fantastizoo.models.creatures.*;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Flyer;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Runner;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Aquarium;
import fr.g4zoo.fantastizoo.models.enclosures.Aviary;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

/**
 * The type Zoo app controller.
 */
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

    /**
     * The Enclosure list transfer.
     */
    @FXML
    public ChoiceBox<String> enclosureListTransfer;

    private Zoo zoo;

    private ZooMaster zooMaster;

    private Enclosure selectedEnclosure;

    private Map<String, Integer> creatureIdMap = new HashMap<>();

    private Creature selectedCreature;

    private Watch watch;

    private Thread watchThread;

    private Thread timeUpdateThread;

    private Thread periodicUpdateThread;

    /**
     * Gets zoo.
     *
     * @return the zoo
     */
    public Zoo getZoo() {
        return zoo;
    }

    /**
     * Gets selected enclosure.
     *
     * @return the selected enclosure
     */
    public Enclosure getSelectedEnclosure() {
        return selectedEnclosure;
    }

    /**
     * Gets selected creature.
     *
     * @return the selected creature
     */
    public Creature getSelectedCreature() {
        return selectedCreature;
    }

    /**
     * The Welcome thread.
     */
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

    private void startPeriodicUpdateThread() {
        periodicUpdateThread = new Thread(() -> {
            try {
                while (true) {
                    zoo.periodicUpdate();

                    Thread.sleep(15000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        periodicUpdateThread.setDaemon(true);
        periodicUpdateThread.start();
    }

    /**
     * Initialize.
     *
     * @param master  the master
     * @param zooName the zoo name
     * @throws IOException the io exception
     */
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
        this.zooMaster = master;
        Platform.runLater(() -> {
            this.zooName.setText(zooName);
            this.zooMasterName.setText(master.getName());
            this.zooMasterHp.setText(master.getHp() + "");
        });

        /**
         * Initialize the enclosures.
         */
        Aviary aviary1 = new Aviary("Phénix 1", 500.0, 20.0);
        Aviary aviary2 = new Aviary("Phénix 2", 800.0, 30.0);
        Enclosure enclosure1 = new Enclosure("Dragon 1", 800.0);
        Enclosure enclosure2 = new Enclosure("Dragon 2", 800.0);
        Aquarium aquarium1 = new Aquarium("Kraken 1", 800.0, 100, 50);
        Aquarium aquarium2 = new Aquarium("Kraken 2", 800.0, 100, 50);
        Enclosure enclosure3 = new Enclosure("Lycanthrope 1", 800.0);
        Enclosure enclosure4 = new Enclosure("Lycanthrope 2", 800.0);
        Aquarium aquarium3 = new Aquarium("Megalodon 1", 800.0, 100, 50);
        Aquarium aquarium4 = new Aquarium("Megalodon 2", 800.0, 100, 50);
        Aquarium aquarium5 = new Aquarium("Mermaid 1", 800.0, 100, 50);
        Aquarium aquarium6 = new Aquarium("Mermaid 2", 800.0, 100, 50);
        Enclosure enclosure5 = new Enclosure("Nymph 1", 800.0);
        Enclosure enclosure6 = new Enclosure("Nymph 2", 800.0);
        Aviary aviary3 = new Aviary("Unicorn 1", 500.0, 20.0);
        Aviary aviary4 = new Aviary("Unicorn 2", 800.0, 30.0);
        /**
         * Initialize the creatures.
         */
        Phoenix phoenixMale = new Phoenix("Ember", aviary1, 'm', 15, 10.0, 2.0);
        Phoenix phoenixFemale = new Phoenix("Blaze", aviary1, 'f', 12, 8.0, 1.8);
        Phoenix newPhoenixMale = new Phoenix("Fawkes", aviary2, 'm', 20, 12.0, 2.2);
        Phoenix newPhoenixFemale = new Phoenix("Pyro", aviary2, 'f', 18, 9.0, 2.0);
        Dragon dragonMale = new Dragon("Draco", enclosure1, 'm', 15, 80.0, 2.5);
        Dragon dragonFemale = new Dragon("Saphira", enclosure2, 'f', 15, 75.0, 2.3);
        Dragon dragonMale2 = new Dragon("Toothless", enclosure1, 'm', 20, 70.0, 2.0);
        Kraken krakenMale = new Kraken("Tentaculus", aquarium1, 'm', 15, 2000.0, 5.0);
        Kraken krakenFemale = new Kraken("Coralus", aquarium2, 'f', 15, 1800.0, 4.5);
        Kraken krakenFemale2 = new Kraken("Squidicus", aquarium2, 'f', 18, 1900.0, 4.7);
        Lycanthrope lycanMale = new Lycanthrope("Fenrir", enclosure3, 'm', 15, 150.0, 1.8);
        Lycanthrope lycanFemale = new Lycanthrope("Lupa", enclosure4, 'f', 15, 140.0, 1.7);
        Lycanthrope lycanMale2 = new Lycanthrope("Remus", enclosure3, 'm', 25, 160.0, 1.9);
        Megalodon megaMale = new Megalodon("Leviathan", aquarium3, 'm', 15, 3000.0, 6.0);
        Megalodon megaFemale = new Megalodon("Tsunami", aquarium4, 'f', 15, 2800.0, 5.5);
        Megalodon megaFemale2 = new Megalodon("Riptide", aquarium4, 'f', 22, 2600.0, 5.2);
        Mermaid mermaidMale = new Mermaid("Ariel", aquarium5, 'f', 15, 50.0, 1.5);
        Mermaid mermaidFemale = new Mermaid("Nerissa", aquarium6, 'f', 15, 48.0, 1.4);
        Mermaid mermaidMale2 = new Mermaid("Marina", aquarium5, 'f', 21, 45.0, 1.4);
        Nymph nyphMale = new Nymph("Aurora", enclosure5, 'm', 15, 0.5, 0.3);
        Nymph nyphFemale = new Nymph("Nova", enclosure6, 'f', 15, 0.4, 0.25);
        Nymph nyphFemale2 = new Nymph("Luna", enclosure5, 'f', 20, 0.6, 0.35);
        Unicorn unicornMale = new Unicorn("Celestia", aviary3, 'm', 15, 550.0, 1.7);
        Unicorn unicornFemale = new Unicorn("Luna", aviary4, 'f', 15, 520.0, 1.6);
        Unicorn unicornMale2 = new Unicorn("Sparkle", aviary3, 'm', 18, 500.0, 1.6);

        zoo.addEnclosure(aviary1);
        zoo.addEnclosure(aviary2);
        zoo.addEnclosure(aviary3);
        zoo.addEnclosure(aviary4);
        zoo.addEnclosure(enclosure1);
        zoo.addEnclosure(enclosure2);
        zoo.addEnclosure(enclosure3);
        zoo.addEnclosure(enclosure4);
        zoo.addEnclosure(enclosure5);
        zoo.addEnclosure(enclosure6);
        zoo.addEnclosure(aquarium1);
        zoo.addEnclosure(aquarium2);
        zoo.addEnclosure(aquarium3);
        zoo.addEnclosure(aquarium4);
        zoo.addEnclosure(aquarium5);
        zoo.addEnclosure(aquarium6);

        startPeriodicUpdateThread();
        Platform.runLater(this::updateUI);

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

                txt_life.setText("" + selectedCreature.getHealth());
                txt_satiety.setText("" + selectedCreature.getSatiety());
                txt_creatureName.setText(selectedCreature.getName());
                txt_creatureAge.setText(selectedCreature.getAge() + " ans");
                txt_creatureHeight.setText(selectedCreature.getHeight() + " m");
                txt_creatureWeight.setText(selectedCreature.getWeight() + " kg");
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
    /**
     * Display the enclosure list.
     * Display total amount of creatures
     */
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

    /**
     * update the Creature List View.
     *
     * @param enclosure  the enclosure
     */
    private void updateCreatureListView(Enclosure enclosure) {
        creatureListView.getItems().clear();
        creatureIdMap.clear();
        enclosure.getCreatures().forEach(creature -> {
            String creatureInfo = String.format("%s",
                    creature.getName());
            creatureListView.getItems().add(creatureInfo);
            creatureIdMap.put(creatureInfo, creature.getId());
        });
    }

    /**
     * update the data oh the selected enclosure.
     */
    private void updateSelectedEnclosure(String enclosureName) {
        Platform.runLater(() -> {
            if (enclosureName != null) {
                selectedEnclosure = zoo.getEnclosureByName(enclosureName);
                if (selectedEnclosure != null) {
                    enclosure_area.setText(selectedEnclosure.getArea() + " m2");
                    enclosure_capacity.setText(selectedEnclosure.getCreatureNumber() + "/" + selectedEnclosure.getMaxCapacity());
                    enclosure_cleanliness_bar.setProgress((double) selectedEnclosure.getCleanliness() / 100.0);
                    enclosure_cleanliness.setText(selectedEnclosure.getCleanliness() > 75 ? "Propre" : selectedEnclosure.getCleanliness() > 50 ? "Correct" : "Sale");
                    updateCreatureListView(selectedEnclosure);
                }
            }
        });
    }

    /**
     * update the data oh the selected creature.
     */
    private void updateSelectedCreature(String creatureInfo) {
        Platform.runLater(() -> {
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
        });
    }

    /**
     * Update enclosure list transfer.
     */
    void updateEnclosureListTransfer() {
        if (this.getZoo() != null) {
            Zoo zoo = this.getZoo();

            Platform.runLater(() -> {
                enclosureListTransfer.getItems().clear();

                for (Enclosure enclosure : zoo.getAllEnclosures()) {
                    if (!enclosure.equals(this.getSelectedEnclosure())) {
                        if (!enclosure.getCreatures().isEmpty() && enclosure.getCreatures().getFirst().getClass().equals(this.getSelectedCreature().getClass())) {
                            enclosureListTransfer.getItems().add(enclosure.getName());
                        }
                    }
                }
            });
        }
    }

    /**
     * On click heal.
     *
     * @param event the event
     */
    @FXML
    public void onClickHeal(ActionEvent event) {
        if (!this.selectedCreature.isDead()) {
            this.getSelectedCreature().healing(40);
            System.out.println(getSelectedCreature().getName() + " a été soigné.");
            updateSelectedCreature(creatureListView.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * On click train.
     *
     * @param event the event
     */
    @FXML
    public void onClickTrain(ActionEvent event) {
        if (!this.selectedCreature.isDead()) {
            if (this.getSelectedCreature() instanceof Flyer) {
                ((Flyer) this.getSelectedCreature()).fly();
                reduceWeight(0.05);
            } else if (this.getSelectedCreature() instanceof Swimmer) {
                ((Swimmer) this.getSelectedCreature()).swim();
                reduceWeight(0.05);
            } else if (this.getSelectedCreature() instanceof Runner) {
                ((Runner) this.getSelectedCreature()).run();
                reduceWeight(0.05);
            } else {
                reduceWeight(0.03);
                System.out.println(getSelectedCreature().getName() + " s'entraine.");
            }
            updateSelectedCreature(creatureListView.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * reduce weight.
     *
     * @param amount the amount of the reduction
     */
    private void reduceWeight(double amount) {
        double currentWeight = this.getSelectedCreature().getWeight();
        double newWeight = currentWeight - amount;
        if (newWeight < 0) {
            newWeight = 0;
        }
        this.getSelectedCreature().setWeight(newWeight);
    }

    /**
     * On click transfer.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onClickTransfer(ActionEvent actionEvent) {
        if (!this.selectedCreature.isDead()) {
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
    }

    /**
     * On click clean.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onClickClean(ActionEvent actionEvent) {
        Enclosure selectedEnclosure = getSelectedEnclosure();
        int cleanliness = selectedEnclosure.getCleanliness();
        if (cleanliness == 100) {
            System.out.println("L'enclos est déjà propre.");
        } else {
            List<Creature> deadCreatures = new ArrayList<>();
            for (Creature creature : selectedEnclosure.getCreatures()) {
                if (creature.isDead()) {
                    deadCreatures.add(creature);
                }
            }
            for (Creature deadCreature : deadCreatures) {
                selectedEnclosure.removeCreature(deadCreature);
                System.out.println(deadCreature.getName() + " a été retiré de l'enclos car il est mort.");
            }

            boolean lifeLost = selectedEnclosure.getCleaned(zoo.getZooMaster());
            updateSelectedEnclosure(selectedEnclosure.getName());

            if (lifeLost) {
                Platform.runLater(() -> {
                    this.zooMasterHp.setText(String.valueOf(zoo.getZooMaster().getHp()));
                    if (zoo.getZooMaster().getHp() <= 0) {
                        showGameOverView(watch.getTime());
                    }
                });
            }
        }
    }

    /**
     * show game overview.
     *
     * @param timeValue the time for the game over screen
     */
    private void showGameOverView(String timeValue) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/g4zoo/fantastizoo/gameover.fxml"));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setTime(timeValue);

            Scene scene = new Scene(root);
            Stage stage = (Stage) zooName.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * On click feed.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onClickFeed(ActionEvent actionEvent) {
        getSelectedEnclosure().feedCreatures();
        updateSelectedCreature(creatureListView.getSelectionModel().getSelectedItem());
    }

}
