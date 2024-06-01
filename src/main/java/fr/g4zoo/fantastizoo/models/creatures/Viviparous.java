package fr.g4zoo.fantastizoo.models.creatures;

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

    public void giveBirth() {
        if (this.getGender() != 'f' || this.getAge() < 18) {
            System.out.println(this.getName() + " ne peut pas donner naissance.");
            return;
        }

        if (this.isPregnant) {
            System.out.println(this.getName() + " est déjà enceinte et ne peut pas retomber enceinte.");
            return;
        }

        System.out.println(this.getName() + " est enceinte.");
        this.setPregnant(true);
        simulateGestationPeriod(() -> {
            Creature baby = createBaby();
            System.out.println(this.getName() + " a donné naissance à un bébé : " + baby.getName() + " !");
            addBabyToZoo(baby);
            this.setPregnant(false);
        });
    }

    protected abstract Creature createBaby();

    protected abstract void addBabyToZoo(Creature baby);

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
