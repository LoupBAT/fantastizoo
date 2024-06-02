package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

public abstract class Viviparous extends Creature {
    private int gestationLength;
    private boolean isPregnant;

    // GETTERS
    public int getGestationLength() {
        return gestationLength;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    // SETTERS
    public void setGestationLength(int gestationLength) {
        this.gestationLength = gestationLength;
    }

    public void setPregnant(boolean isPregnant) {
        this.isPregnant = isPregnant;
    }

    public void giveBirth(Enclosure enclosure) {
        if (this.getGender() != 'f' || this.getAge() < 18) {
            return;
        }

        if (this.isPregnant) {
            return;
        }

        System.out.println(enclosure.getName() + ": " + this.getName() + " est enceinte.");
        this.setPregnant(true);
        simulateGestationPeriod(() -> {
            Creature baby = createBaby();
            System.out.println(enclosure.getName() + ": " + this.getName() + " a donné naissance à un bébé : " + baby.getName() + " !");
            this.setPregnant(false);
        });
    }

    protected abstract Creature createBaby();

    protected void simulateGestationPeriod(Runnable onBirth) {
        new Thread(() -> {
            try {
                Thread.sleep(gestationLength * 1000L);
                onBirth.run();
            } catch (InterruptedException e) {
                System.err.println("La gestation a été interrompue.");
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
