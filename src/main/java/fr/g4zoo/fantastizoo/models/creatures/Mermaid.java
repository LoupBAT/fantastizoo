package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Mermaid extends Viviparous implements Swimmer {
    private static final int DEFAULT_GESTATION_PERIOD = 12;
    private static final String[] NAMES = {"Ariel", "Marina", "Nerissa", "Ondine", "Sirena", "Thalassa"};
    private Enclosure enclosure;

    // Constructor
    public Mermaid(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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
        double weight = generateRandomWeight(2.0, 4.0);
        double height = generateRandomHeight(0.5, 1.0);

        return new Mermaid(babyName, this.enclosure, gender, 1, weight, height);
    }

    @Override
    protected void addBabyToZoo(Creature baby) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(baby);
        }
        System.out.println("Bébé sirène ajouté au zoo : " + baby.getName());
    }

    @Override
    public void swim() {
        System.out.println(this.getName() + " nage avec élégance !");
    }
}
