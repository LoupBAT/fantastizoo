package fr.g4zoo.fantastizoo.models.creatures;

public abstract class Viviparous extends Creature {
    private int gestationLength;

    // GETTERS
    public int getGestationLength() {
        return gestationLength;
    }

    // SETTERS
    public void setGestationLength(int gestationLength) {
        this.gestationLength = gestationLength;
    }

    public abstract void giveBirth();

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

