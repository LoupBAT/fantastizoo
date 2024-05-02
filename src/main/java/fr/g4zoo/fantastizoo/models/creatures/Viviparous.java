package fr.g4zoo.fantastizoo.models.creatures;

public abstract class Viviparous extends Creature{
    private int gestationLength ;


    // GETTERS

    public int getGestationLength() {
        return gestationLength;
    }

    // SETTERS

    public void setGestationLength(int gestationLength) {
        this.gestationLength = gestationLength;
    }

    public abstract void giveBirth();
}
