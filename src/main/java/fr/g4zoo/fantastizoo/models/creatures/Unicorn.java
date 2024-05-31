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
        enclosure.addCreature(this);
    }

    @Override
    public void giveBirth() {
        if (this.getGender() != 'f' || this.getAge() < 18) {
            System.out.println(this.getName() + " ne peut pas donner naissance.");
            return;
        }

        System.out.println(this.getName() + " est enceinte.");
        simulateGestationPeriod(() -> {
            Unicorn babyUnicorn = createBabyUnicorn();
            System.out.println(this.getName() + " a donné naissance à un bébé licorne : " + babyUnicorn.getName() + " !");
            addBabyUnicornToZoo(babyUnicorn);
        });
    }

    private Unicorn createBabyUnicorn() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(3.0, 5.0);
        double height = generateRandomHeight(0.7, 1.2);

        return new Unicorn(babyName, this.enclosure, gender, 1, weight, height);
    }

    private void addBabyUnicornToZoo(Unicorn babyUnicorn) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(babyUnicorn);
        }
        System.out.println("Bébé licorne ajouté au zoo : " + babyUnicorn.getName());
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " court avec grâce !");
    }
}
