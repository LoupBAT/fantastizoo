package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Runner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Lycanthrope extends Viviparous implements Runner {
    private static final int DEFAULT_GESTATION_PERIOD = 9;
    private static final String[] NAMES = {"Fenrir", "Remus", "Lupa", "Lycus", "Theron", "Ulric"};
    private Enclosure enclosure;

    // Constructor
    public Lycanthrope(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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
            Lycanthrope babyLycanthrope = createBabyLycanthrope();
            System.out.println(this.getName() + " a donné naissance à un bébé lycanthrope : " + babyLycanthrope.getName() + " !");
            addBabyLycanthropeToZoo(babyLycanthrope);
        });
    }

    private Lycanthrope createBabyLycanthrope() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(3.0, 5.0);
        double height = generateRandomHeight(0.7, 1.2);

        return new Lycanthrope(babyName, this.enclosure, gender, 1, weight, height);
    }

    private void addBabyLycanthropeToZoo(Lycanthrope babyLycanthrope) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(babyLycanthrope);
        }
        System.out.println("Bébé lycanthrope ajouté au zoo : " + babyLycanthrope.getName());
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " court à pleine vitesse !");
    }
}
