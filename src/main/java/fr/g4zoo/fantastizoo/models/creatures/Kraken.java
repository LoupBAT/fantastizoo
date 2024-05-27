package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Kraken extends Oviparous implements Swimmer {

    private static final int DEFAULT_INCUBATION_PERIOD = 5;
    private static final String[] NAMES = {"Tentaculus", "Squidicus", "Inkspire", "Coralus", "Neptunus"};

    private Enclosure enclosure;

    // Constructor
    public Kraken(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
        this.setName(name);
        this.setIncubationPeriod(DEFAULT_INCUBATION_PERIOD);
        this.enclosure = enclosure;
        this.setGender(gender);
        this.setAge(age);
        this.setWeight(weight);
        this.setHeight(height);
        enclosure.addCreature(this);
    }

    @Override
    public void layEgg() {
        if (this.getGender() != 'f' || this.getAge() < 10) {
            System.out.println(this.getName() + " ne peut pas pondre d'œuf.");
            return;
        }

        System.out.println(this.getName() + " a pondu un œuf.");
        simulateIncubationPeriod(() -> {
            Kraken babyKraken = createBabyKraken();
            System.out.println("L'œuf de " + getName() + " a éclos et un bébé kraken est né : " + babyKraken.getName() + " !");
            addBabyKrakenToZoo(babyKraken);
        });
    }

    @Override
    public void swim() {
        System.out.println(this.getName() + " nage !");
    }

    private Kraken createBabyKraken() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(2000.0, 3000.0);
        double height = generateRandomHeight(5.0, 8.0);

        return new Kraken(babyName, this.enclosure, gender, 1, weight, height);
    }

    private void addBabyKrakenToZoo(Kraken babyKraken) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(babyKraken);
        }
        System.out.println("Bébé kraken ajouté au zoo: " + babyKraken.getName());
    }
}
