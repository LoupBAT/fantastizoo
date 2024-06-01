package fr.g4zoo.fantastizoo.models.enclosures;

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

    public void displayInformations() {
        System.out.println("Nom de l'enclos : " + name);
        System.out.println("Superficie : " + area + " m²");
        System.out.println("Capacité maximale : " + maxCapacity);
        System.out.println("Nombre de créatures : " + creatureNumber);
        System.out.println("Propreté : " + cleanliness + "%");
        System.out.println("Créatures : " + creatures);
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
            System.out.println("Les créatures ont été nourries.");
            for (Creature creature : creatures) {
                creature.eat(10);
            }
        } else {
            System.out.println("Il n'y a pas de créature à nourrir.");
        }
    }

    public void getCleaned() {
        cleanliness = 100;
        System.out.println("L'enclos a été nettoyé. Propreté actuelle : " + cleanliness + "%");
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
        int decrease = RANDOM.nextInt(6);
        cleanliness -= decrease;
        if (cleanliness < 0) {
            cleanliness = 0;
        }
    }
}
