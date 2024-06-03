package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Flyer;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Runner;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
/**
 * The type Dragon.
 */
public class Dragon extends Oviparous implements Flyer, Runner, Swimmer, Reborner {

    private static final int DEFAULT_INCUBATION_PERIOD = 10;
    private static final String[] NAMES = {"Draco", "Smaug", "Toothless", "Falkor", "Alduin", "Saphira", "Norbert", "Spyro"};
    private Enclosure enclosure;
    private int rebornCount = 0;
    private static final int MAX_REBORN_COUNT = 3;

    /**
     * Instantiates a new Dragon.
     *
     * @param name      the name
     * @param enclosure the enclosure
     * @param gender    the gender
     * @param age       the age
     * @param weight    the weight
     * @param height    the height
     */
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
    /**
     * When a dragon is trained he can fly
     */
    @Override
    public void fly() {
        System.out.println(this.getName() + " s'envole majestueusement !");
    }

    /**
     * When the Dragon is trained he can run.
     */
    @Override
    public void run() {
        System.out.println(this.getName() + " court à pleine vitesse !");
    }

    /**
     * When the mermaid is trained she can swim.
     */
    @Override
    public void swim() { System.out.println(this.getName() + " nage avec élégance !"); }

    /**
     * Create a baby after the incubation period of the egg
     */
    @Override
    protected Creature createBaby() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(10.0, 50.0);
        double height = generateRandomHeight(1.0, 3.0);

        return new Dragon(babyName, this.enclosure, gender, 1, weight, height);
    }

    /**
     * The dragon can reborn 3 times
     */
    @Override
    public void reborn() {
        if (canReborn()) {
            rebornCount++;
            System.out.println(this.getName() + " renaît !");
            resetToBabyState();
        } else {
            this.setDead(true);
            this.setSatiety(0);
        }
    }

    /**
     * Check if the dragon have reached the max reborn count
     */
    @Override
    public boolean canReborn() {
        return rebornCount < MAX_REBORN_COUNT;
    }

    /**
     * When the dragon have reborned he was reset as a newborn
     */
    private void resetToBabyState() {
        this.setAge(0);
        this.setWeight(generateRandomWeight(10.0, 50.0));
        this.setHeight(generateRandomHeight(1.0, 3.0));
        this.setHealth(100);
    }
}
