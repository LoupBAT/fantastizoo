package fr.g4zoo.fantastizoo.models;

import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Zoo {

    private String name;
    private final ZooMaster zooMaster;
    private final ArrayList<Enclosure> enclosures;
    private final int maxEnclosures;

    // CONSTRUCTER

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

                // TODO: Tâche appeler la periodic update de tout les enclos
            }
        };
        timer.schedule(timerTask, 0, 15000);
    }

    //GETTERS

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

    //SETTERS

    public void setName(String name) {
        this.name = name;
    }
    
}
