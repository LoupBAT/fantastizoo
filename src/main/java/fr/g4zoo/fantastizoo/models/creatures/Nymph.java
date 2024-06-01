package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Nymph extends Viviparous implements Reborner {
    private static final int DEFAULT_GESTATION_PERIOD = 6;
    private static final String[] NAMES = {"Astra", "Luna", "Selene", "Nyx", "Aurora", "Celeste", "Eos", "Nova"};
    private Enclosure enclosure;
    private int rebornCount = 0;

    // Constructor
    public Nymph(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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

        return new Nymph(babyName, this.enclosure, gender, 1, weight, height);
    }

    @Override
    protected void addBabyToZoo(Creature baby) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(baby);
        }
        System.out.println("Bébé nymphe ajouté au zoo : " + baby.getName());
    }

    @Override
    public void reborn() {
        if (canReborn()) {
            rebornCount++;
            System.out.println(this.getName() + " renaît de ses cendres !");
            resetToBabyState();
        } else {
            System.out.println(this.getName() + " ne peut plus renaître.");
        }
    }

    @Override
    public boolean canReborn() {
        return rebornCount < MAX_REBORN_COUNT;
    }

    private void resetToBabyState() {
        this.setAge(0);
        this.setWeight(generateRandomWeight(2.0, 4.0));
        this.setHeight(generateRandomHeight(0.5, 1.0));
        this.setHealth(100);
    }
}
