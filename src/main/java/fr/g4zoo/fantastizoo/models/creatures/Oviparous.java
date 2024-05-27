package fr.g4zoo.fantastizoo.models.creatures;

import java.util.Random;

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

    public abstract void layEgg();

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
