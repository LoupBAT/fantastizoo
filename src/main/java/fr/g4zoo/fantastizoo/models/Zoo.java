package fr.g4zoo.fantastizoo.models;

import fr.g4zoo.fantastizoo.models.creatures.Creature;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
import fr.g4zoo.fantastizoo.models.enclosures.Aviary; // Importer la classe Aviary

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Zoo {

    private String name;
    private final ZooMaster zooMaster;
    private final ArrayList<Enclosure> enclosures;
    private final int maxEnclosures;

    // CONSTRUCTOR

    public Zoo(String name, ZooMaster zooMaster) {
        this.name = name;
        this.zooMaster = zooMaster;
        this.enclosures = new ArrayList<>();
        this.maxEnclosures = 24;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        };
        timer.schedule(timerTask, 0, 15000);
    }

    // GETTERS

    public String getName() {
        return name;
    }

    public ZooMaster getZooMaster() {
        return zooMaster;
    }

    public ArrayList<Enclosure> getEnclosures() {
        return enclosures;
    }

    public Integer getMaxEnclosures() {
        return maxEnclosures;
    }

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void addEnclosure(Enclosure enclosure) {
        if (enclosures.size() < maxEnclosures) {
            enclosures.add(enclosure);
        } else {
            System.out.println("Impossible d'ajouté un enclos limte atteinte.");
        }
    }

    public Enclosure getEnclosureByName(String enclosureName) {
        for (Enclosure enclosure : enclosures) {
            if (enclosure.getName().equals(enclosureName)) {
                return enclosure;
            }
        }
        return null;
    }

    public ArrayList<Enclosure> getAllEnclosures() {
        return new ArrayList<>(enclosures);
    }

    public void periodicUpdate() {
        for (Enclosure enclosure : enclosures) {
            enclosure.periodicUpdate();

            for (Creature creature : enclosure.getCreatures()) {
                creature.periodicUpdate(enclosure);
            }
        }
    }
}
