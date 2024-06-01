package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Runner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Unicorn extends Viviparous implements Runner {
    private static final int DEFAULT_GESTATION_PERIOD = 11;
    private static final String[] NAMES = {"Celestia", "Sparkle", "Blaze", "Luna", "Aurora", "Starlight"};
    private Enclosure enclosure;

    // Constructor
    public Unicorn(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
        this.setName(name);
        this.setGestationLength(DEFAULT_GESTATION_PERIOD);
        this.enclosure = enclosure;
        this.setGender(gender);
        this.setAge(age);
        this.setWeight(weight);
        this.setHeight(height);
        this.setAgeMax(generateRandomAgeMax());
        enclosure.addCreature(this);
    }

    @Override
    protected Creature createBaby() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(3.0, 5.0);
        double height = generateRandomHeight(0.7, 1.2);

        return new Unicorn(babyName, this.enclosure, gender, 1, weight, height);
    }

    @Override
    protected void addBabyToZoo(Creature baby) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(baby);
        }
        System.out.println("Bébé licorne ajouté au zoo : " + baby.getName());
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " court avec grâce !");
    }
}
