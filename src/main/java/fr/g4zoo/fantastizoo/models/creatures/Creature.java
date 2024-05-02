package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;

public abstract class Creature {

    // ATTRIBUTES

    private String name;
    private String species;

    private char gender;
    private int age;
    private double weight;
    private double height;

    private int satiety;
    private boolean isHungry;

    private int health;
    private boolean isAsleep;

    // GETTERS

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getSatiety() {
        return satiety;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAsleep() {
        return isAsleep;
    }

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAsleep(boolean asleep) {
        isAsleep = asleep;
    }


    // METHODS

    public void eat(int satietyPoint) {
        this.setSatiety(this.getSatiety() + satietyPoint);
        this.setHungry(this.getSatiety() <= 40);
        this.setSatiety(Math.min(this.getSatiety(), 100));

        // TODO Tâche 1.2: Méthode eat() - Faire la logique de prise de poids

    }

    public void makeSound(String soundUrl) {

        // TODO Tâche 1.3: Méthode makeSound()

    }

    public void healing(int healingPoint) {
        this.setHealth(this.getHealth() + healingPoint);
        this.setHealth(Math.min(this.getHealth(), 100));
    }

    public void sleep(){
        this.setAsleep(true);
    }

    public void wakeUp() {
        this.setAsleep(false);
    }

    public void growOld(int limitAge){
        this.setAge(this.getAge() + 1);

        // TODO Tâche 1.7: Méthode growOld()

        /*
        if(this.getAge() > limitAge){
            if (this instanceof Reborner){
                ((Reborner) this).reborn();
            }else {
                this.setHealth(0);
            }
        }
        */

    }

    public void periodicUpdate(){

        // TODO Tâche 1.8: Méthode periodicUpdate()

    }

}
