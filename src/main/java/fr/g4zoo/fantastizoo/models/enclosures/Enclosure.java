package fr.g4zoo.fantastizoo.models.enclosures;

import fr.g4zoo.fantastizoo.models.ZooMaster;
import fr.g4zoo.fantastizoo.models.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enclosure {

    private String name;
    private double area;
    private int maxCapacity;
    private int creatureNumber;
    private int cleanliness;
    private final Object cleanlinessLock = new Object();
    private List<Creature> creatures;
    private static final Random RANDOM = new Random();

    // Constructor
    public Enclosure(String name, double area) {
        this.name = name;
        this.area = area;
        this.maxCapacity = 8;
        this.creatureNumber = 0;
        this.cleanliness = 100;
        this.creatures = new ArrayList<>();
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCreatureNumber() {
        return creatureNumber;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void addCreature(Creature creature) {
        if (creatureNumber < maxCapacity) {
            creatures.add(creature);
            creatureNumber++;
        } else {
            System.out.println("Capacité maximale atteinte ! Impossible d'ajouter une créature.");
        }
    }

    public void removeCreature(Creature creature) {
        if (creatures.remove(creature)) {
            creatureNumber--;
        } else {
            System.out.println("La créature n'a pas été trouvée.");
        }
    }

    public void feedCreatures() {
        if (creatureNumber > 0) {
            for (Creature creature : creatures) {
                if (!creature.isDead()) {
                    creature.eat(10);
                }
            }
        } else {
            System.out.println("Il n'y a pas de créature à nourrir.");
        }
    }

    public boolean getCleaned(ZooMaster zooMaster) {
        boolean lifeLost = false;
        synchronized (cleanlinessLock) {
            cleanliness = 100;
            System.out.println("L'enclos a été nettoyé. Propreté actuelle : " + cleanliness + "%");

            boolean hasAwakeCreature = false;
            for (Creature creature : creatures) {
                if (!creature.isAsleep() && !creature.isDead()) {
                    hasAwakeCreature = true;
                    break;
                }
            }

            if (hasAwakeCreature) {
                double random = Math.random();
                if (random <= 0.05) {
                    zooMaster.setHp(zooMaster.getHp() - 1);
                    lifeLost = true;
                    System.out.println("Oh non ! " + zooMaster.getName() + " s'est fait mordre par une créature et a perdu une jambe ! PV restants : " + zooMaster.getHp());
                    if (zooMaster.getHp() <= 0) {
                        System.out.println(zooMaster.getName() + " est décédé à cause de ses blessures !");
                    }
                }
            }
        }
        return lifeLost;
    }

    public Creature getCreatureById(int id){
        for (Creature creature : creatures) {
            if (creature.getId() == id) {
                return creature;
            }
        }
        return null;
    }

    public void periodicUpdate() {
        synchronized (cleanlinessLock) {
            int decrease = RANDOM.nextInt(6);
            cleanliness -= decrease;
            if (cleanliness < 0) {
                cleanliness = 0;
            }
        }
    }
}
