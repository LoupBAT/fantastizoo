package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Swimmer;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public class Megalodon extends Oviparous implements Swimmer {

    private static final int DEFAULT_INCUBATION_PERIOD = 5;
    private static final String[] NAMES = {"Leviathan", "Tsunami", "Poseidon", "Jaws", "Riptide", "Shadow", "Goliath", "Behemoth", "Megalodon"};
    private Enclosure enclosure;

    // Constructor
    public Megalodon(String name, Enclosure enclosure, char gender, int age, double weight, double height) {
        this.setName(name);
        this.setIncubationPeriod(DEFAULT_INCUBATION_PERIOD);
        this.enclosure = enclosure;
        this.setGender(gender);
        this.setAge(age);
        this.setWeight(weight);
        this.setHeight(height);
        enclosure.addCreature(this);
    }

    @Override
    public void layEgg() {
        if (this.getGender() != 'f' || this.getAge() < 10) {
            System.out.println(this.getName() + " ne peut pas pondre d'œuf.");
            return;
        }

        System.out.println(this.getName() + " a pondu un œuf.");
        simulateIncubationPeriod(() -> {
            Megalodon babyMegalodon = createBabyMegalodon();
            System.out.println("L'œuf de " + getName() + " a éclos et un bébé megalodon est né : " + babyMegalodon.getName() + " !");
            addBabyMegalodonToZoo(babyMegalodon);
        });
    }

    @Override
    public void swim() {
        System.out.println(this.getName() + " nage !");
    }

    private Megalodon createBabyMegalodon() {
        String babyName = generateRandomName(NAMES);
        char gender = generateRandomGender();
        double weight = generateRandomWeight(500.0, 700.0);
        double height = generateRandomHeight(2.0, 3.0);

        return new Megalodon(babyName, this.enclosure, gender, 1, weight, height);
    }

    private void addBabyMegalodonToZoo(Megalodon babyMegalodon) {
        if (this.enclosure != null) {
            this.enclosure.addCreature(babyMegalodon);
        }
        System.out.println("Bébé megalodon ajouté au zoo: " + babyMegalodon.getName());
    }
}
