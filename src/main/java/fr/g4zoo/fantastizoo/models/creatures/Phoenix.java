package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Flyer;
import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;
import java.util.Random;

public class Phoenix extends Oviparous implements Flyer, Reborner {

    private static final int DEFAULT_INCUBATION_PERIOD = 5;
    private static final Random RANDOM = new Random();

    // Constructor
    public Phoenix(String name) {
        this.setName(name);
        this.setIncubationPeriod(DEFAULT_INCUBATION_PERIOD);
    }

    @Override
    public void layEgg() {
        if (this.getGender() != 'f' || this.getAge() < 10) {
            System.out.println(this.getName() + " ne peut pas pondre d'œuf.");
            return;
        }

        System.out.println(this.getName() + " a pondu un œuf.");
        int incubationPeriod = getIncubationPeriod();

        // Simulate incubation period with a thread
        new Thread(() -> {
            try {
                Thread.sleep(incubationPeriod * 1000L);
                Phoenix babyPhoenix = createBabyPhoenix();
                System.out.println("L'œuf de " + getName() + " a éclos et un bébé phénix est né : " + babyPhoenix.getName() + " !");
                addBabyPhoenixToZoo(babyPhoenix);
            } catch (InterruptedException e) {
                System.err.println("L'incubation de l'œuf de " + getName() + " a été interrompue.");
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    @Override
    public void fly() {
        System.out.println(this.getName() + " s'envole majestueusement !");
    }

    private Phoenix createBabyPhoenix() {
        String babyName = generateRandomName();
        char gender = generateRandomGender();
        double weight = generateRandomWeight(1.0, 3.0);
        double height = generateRandomHeight(0.2, 0.5);

        Phoenix babyPhoenix = new Phoenix(babyName);
        babyPhoenix.setGender(gender);
        babyPhoenix.setAge(0);
        babyPhoenix.setWeight(weight);
        babyPhoenix.setHeight(height);

        return babyPhoenix;
    }

    private void addBabyPhoenixToZoo(Phoenix babyPhoenix) {
        // TODO Add the baby to the same enclosure as his mom
        System.out.println("Bébé phénix ajouté au zoo: " + babyPhoenix.getName());
    }

    private String generateRandomName() {
        String[] names = {"Fawkes", "Pyro", "Blaze", "Ember", "Flare", "Inferno", "Sparky", "Ash"};
        return names[RANDOM.nextInt(names.length)];
    }



}
