package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

import java.util.Random;

public abstract class Creature {

    private static final Random RANDOM = new Random();

    // ATTRIBUTES

    private static int nextId = 1;

    private final int id;

    private String name;
    private String species;

    private char gender;
    private int age;
    private int ageMax;
    private double weight;
    private double height;

    private int satiety = 100;
    private boolean isHungry;

    private int health = 100;
    private boolean isAsleep;

    // CONSTRUCTORS

    public Creature() {
        this.id = nextId++;
    }

    // GETTERS

    public int getId() {
        return id;
    }

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

    public int getAgeMax() {
        return ageMax;
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

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
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
        int newSatiety = this.getSatiety() + satietyPoint;
        this.setSatiety(newSatiety);

        if (this.getSatiety() <= 40) {
            this.setHungry(true);
        }

        if (this.getSatiety() <= 100) {
            this.setWeight(this.getWeight() + satietyPoint * 0.1);
        }

        if (this.getSatiety() > 100) {
            int newHealth = this.getHealth() - 5;
            this.setHealth(newHealth);
            System.out.println("Attention "+this.getName() + " perd de la vie à force de trop manger !");
        }

        if (this.getWeight() < 0) {
            this.setWeight(0);
        }
        if (this.getHealth() < 0) {
            this.setHealth(0);
        }
    }

    public void makeSound(String soundUrl) {
        try {
            URL url = new URL(soundUrl);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
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

    public void growOld() {
        this.setAge(this.getAge() + 1);

        if (this.getAge() > ageMax) {
            if (this instanceof Reborner) {
                if (((Reborner) this).canReborn()) {
                    ((Reborner) this).reborn();
                } else {
                    this.setHealth(0);
                }
            } else {
                this.setHealth(0);
            }
        }
    }


    public void periodicUpdate(){

        // TODO Tâche 1.8: Méthode periodicUpdate()

    }

    protected String generateRandomName(String[] names) {
        return names[RANDOM.nextInt(names.length)];
    }

    protected char generateRandomGender() {
        return RANDOM.nextBoolean() ? 'm' : 'f';
    }

    protected double generateRandomWeight(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    protected double generateRandomHeight(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    protected int generateRandomAgeMax() {
        Random random = new Random();
        return random.nextInt(131) + 20;
    }
}
