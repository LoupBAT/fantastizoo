package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
/**
 * The type Megalodon.
 */
public class Megalodon extends Oviparous implements Swimmer {

    private static final int DEFAULT_INCUBATION_PERIOD = 5;
    private static final String[] NAMES = {"Leviathan", "Tsunami", "Poseidon", "Jaws", "Riptide", "Shadow", "Goliath", "Behemoth", "Megalodon"};
    private Enclosure enclosure;

    /**
     * Instantiates a new Megalodon.
     *
     * @param name      the name
     * @param enclosure the enclosure
     * @param gender    the gender
     * @param age       the age
     * @param weight    the weight
     * @param height    the height
     */
// Constructor
    public Megalodon(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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
     * When the megalodon is trained he can swim.
     */
    @Override
    public void swim() {
        System.out.println(this.getName() + " nage !");
    }
    /**
     * Create a baby after the incubation period of the egg
     */
    @Override
    protected Creature createBaby() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(500.0, 700.0);
        double height = generateRandomHeight(2.0, 3.0);

        return new Megalodon(babyName, this.enclosure, gender, 1, weight, height);
    }
}
