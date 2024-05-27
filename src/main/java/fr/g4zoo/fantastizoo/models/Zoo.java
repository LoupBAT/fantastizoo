package fr.g4zoo.fantastizoo.models;

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
                System.out.println("Starting timer");
                // TODO: Tâche appelée périodiquement pour la mise à jour de tous les enclos
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
            System.out.println("Enclosure added to the zoo: " + enclosure.getName());
        } else {
            System.out.println("Cannot add enclosure. Maximum enclosures reached.");
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
        ArrayList<Enclosure> allEnclosures = new ArrayList<>();
        for (Enclosure enclosure : enclosures) {
            allEnclosures.add(enclosure);
        }
        return allEnclosures;
    }
}
