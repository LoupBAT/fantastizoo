package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public abstract class Oviparous extends Creature {

    private int incubationPeriod;

    // GETTERS
    public int getIncubationPeriod() {
        return incubationPeriod;
    }

    // SETTERS
    public void setIncubationPeriod(int incubationPeriod) {
        this.incubationPeriod = incubationPeriod;
    }

    public void layEgg(Enclosure enclosure) {
        if (this.getGender() != 'f' || this.getAge() < 10) {
            return;
        }

        System.out.println(enclosure.getName() + ": " + this.getName() + " a pondu un œuf.");
        simulateIncubationPeriod(() -> {
            Creature baby = createBaby();
            System.out.println(enclosure.getName() + ": L'œuf de " + getName() + " a éclos et un bébé est né : " + baby.getName() + " !");
        });
    }

    protected abstract Creature createBaby();

    protected void simulateIncubationPeriod(Runnable onHatch) {
        new Thread(() -> {
            try {
                Thread.sleep(incubationPeriod * 1000L);
                onHatch.run();
            } catch (InterruptedException e) {
                System.err.println("L'incubation de l'œuf a été interrompue.");
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
