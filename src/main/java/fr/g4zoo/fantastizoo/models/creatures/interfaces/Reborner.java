package fr.g4zoo.fantastizoo.models.creatures.interfaces;

public interface Reborner {
    int MAX_REBORN_COUNT = 3;

    void reborn();
    boolean canReborn();
}
