package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Kraken extends Oviparous implements Swimmer {

    private static final int DEFAULT_INCUBATION_PERIOD = 5;
    private static final String[] NAMES = {"Tentaculus", "Squidicus", "Inkspire", "Coralus", "Neptunus"};

    private Enclosure enclosure;

    /**
     * Instantiates a new Kraken.
     *
     * @param name      the name
     * @param enclosure the enclosure
     * @param gender    the gender
     * @param age       the age
     * @param weight    the weight
     * @param height    the height
     */
// Constructor
    public Kraken(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
        this.setName(name);
        this.setIncubationPeriod(DEFAULT_INCUBATION_PERIOD);
        this.enclosure = enclosure;
        this.setGender(gender);
        this.setAge(age);
        this.setWeight(weight);
        this.setHeight(height);
        this.setAgeMax(generateRandomAgeMax());
        enclosure.addCreature(this);
    }

    @Override
    public void swim() {
        System.out.println(this.getName() + " nage !");
    }

    @Override
    protected Creature createBaby() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(2000.0, 3000.0);
        double height = generateRandomHeight(5.0, 8.0);

        return new Kraken(babyName, this.enclosure, gender, 1, weight, height);
    }
}
