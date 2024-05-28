package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Nymph extends Viviparous implements Reborner {
    private static final int DEFAULT_GESTATION_PERIOD = 6;
    private static final String[] NAMES = {"Astra", "Luna", "Selene", "Nyx", "Aurora", "Celeste", "Eos", "Nova"};
    private Enclosure enclosure;

    // Constructor
    public Nymph(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
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
            Nymph babyNymph = createBabyNymph();
            System.out.println(this.getName() + " a donné naissance à une bébé nymphe : " + babyNymph.getName() + " !");
            addBabyNymphToZoo(babyNymph);
        });
    }

    private Nymph createBabyNymph() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(2.0, 4.0);
        double height = generateRandomHeight(0.5, 1.0);

        return new Nymph(babyName, this.enclosure, gender, 1, weight, height);
    }

    private void addBabyNymphToZoo(Nymph babyNymph) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(babyNymph);
        }
        System.out.println("Bébé nymphe ajouté au zoo : " + babyNymph.getName());
    }
}
