package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Flyer;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Phoenix extends Oviparous implements Flyer, Reborner {

    private static final int DEFAULT_INCUBATION_PERIOD = 5;
    private static final String[] NAMES = {"Fawkes", "Pyro", "Blaze", "Ember", "Flare", "Inferno", "Sparky", "Ash"};
    private Enclosure enclosure;
    private int rebornCount = 0;

    public Phoenix(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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
    protected Creature createBaby() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(1.0, 3.0);
        double height = generateRandomHeight(0.2, 0.5);

        return new Phoenix(babyName, this.enclosure, gender, 1, weight, height);
    }

    @Override
    protected void addBabyToZoo(Creature baby) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(baby);
        }
        System.out.println("Bébé phénix ajouté au zoo: " + baby.getName());
    }

    @Override
    public void fly() {
        System.out.println(this.getName() + " s'envole majestueusement !");
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
        this.setWeight(generateRandomWeight(1.0, 3.0));
        this.setHeight(generateRandomHeight(0.2, 0.5));
        this.setHealth(100);
    }
}