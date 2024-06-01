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

    public void layEgg() {
        if (this.getGender() != 'f' || this.getAge() < 10) {
            return;
        }

        System.out.println(this.getName() + " a pondu un œuf.");
        simulateIncubationPeriod(() -> {
            Creature baby = createBaby();
            System.out.println("L'œuf de " + getName() + " a éclos et un bébé est né : " + baby.getName() + " !");
            addBabyToZoo(baby);
        });
    }

    protected abstract Creature createBaby();

    protected abstract void addBabyToZoo(Creature baby);

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
