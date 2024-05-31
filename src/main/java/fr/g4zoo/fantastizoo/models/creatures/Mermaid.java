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
    public void giveBirth() {
        if (this.getGender() != 'f' || this.getAge() < 18) {
            System.out.println(this.getName() + " ne peut pas donner naissance.");
            return;
        }

        System.out.println(this.getName() + " est enceinte.");
        simulateGestationPeriod(() -> {
            Mermaid babyMermaid = createBabyMermaid();
            System.out.println(this.getName() + " a donné naissance à une bébé sirène : " + babyMermaid.getName() + " !");
            addBabyMermaidToZoo(babyMermaid);
        });
    }

    private Mermaid createBabyMermaid() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(2.0, 4.0);
        double height = generateRandomHeight(0.5, 1.0);

        return new Mermaid(babyName, this.enclosure, gender, 1, weight, height);
    }

    private void addBabyMermaidToZoo(Mermaid babyMermaid) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(babyMermaid);
        }
        System.out.println("Bébé sirène ajouté au zoo : " + babyMermaid.getName());
    }

    @Override
    public void swim() {
        System.out.println(this.getName() + " nage avec élégance !");
    }
}
