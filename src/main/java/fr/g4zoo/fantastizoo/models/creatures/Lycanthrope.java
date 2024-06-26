package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Runner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;
/**
 * The type Lycanthrope.
 */
public class Lycanthrope extends Viviparous implements Runner {
    private static final int DEFAULT_GESTATION_PERIOD = 9;
    private static final String[] NAMES = {"Fenrir", "Remus", "Lupa", "Lycus", "Theron", "Ulric"};
    private Enclosure enclosure;

    /**
     * Instantiates a new Lycanthrope.
     *
     * @param name      the name
     * @param enclosure the enclosure
     * @param gender    the gender
     * @param age       the age
     * @param weight    the weight
     * @param height    the height
     */
// Constructor
    public Lycanthrope(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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
    /**
     * Create a baby after the gestation period of the mom
     */
    @Override
    protected Creature createBaby() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(3.0, 5.0);
        double height = generateRandomHeight(0.7, 1.2);

        return new Lycanthrope(babyName, this.enclosure, gender, 1, weight, height);
    }

    /**
     * When the Lycanthrope is trained he can run.
     */
    @Override
    public void run() {
        System.out.println(this.getName() + " court à pleine vitesse !");
    }
}
