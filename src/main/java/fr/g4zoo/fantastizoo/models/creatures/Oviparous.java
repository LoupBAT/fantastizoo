package fr.g4zoo.fantastizoo.models.creatures;

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
}
