package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Flyer;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Dragon extends Oviparous implements Flyer, Reborner {

    private static final int DEFAULT_INCUBATION_PERIOD = 10;
    private static final String[] NAMES = {"Draco", "Smaug", "Toothless", "Falkor", "Alduin", "Saphira", "Norbert", "Spyro"};
    private Enclosure enclosure;
    private int rebornCount = 0;
    private static final int MAX_REBORN_COUNT = 3;

    // Constructeur
    public Dragon(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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
    public void fly() {
        System.out.println(this.getName() + " s'envole majestueusement !");
    }

    @Override
    protected Creature createBaby() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(10.0, 50.0);
        double height = generateRandomHeight(1.0, 3.0);

        return new Dragon(babyName, this.enclosure, gender, 1, weight, height);
    }

    @Override
    protected void addBabyToZoo(Creature baby) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(baby);
        }
        System.out.println("Bébé dragon ajouté au zoo: " + baby.getName());
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
        this.setWeight(generateRandomWeight(10.0, 50.0));
        this.setHeight(generateRandomHeight(1.0, 3.0));
        this.setHealth(100);
    }
}
