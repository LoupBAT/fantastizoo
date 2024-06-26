package fr.g4zoo.fantastizoo.models.creatures;

import fr.g4zoo.fantastizoo.models.creatures.interfaces.Reborner;
import fr.g4zoo.fantastizoo.models.enclosures.Enclosure;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

import java.util.Random;

/**
 * The type Creature.
 */
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
    private boolean isSick;
    private boolean isDead = false;


    /**
     * Instantiates a new Creature.
     */
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

    public boolean isSick() {
        return isSick;
    }

    public boolean isDead() {return isDead;}

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
        this.weight = roundToTwoDecimalPlaces(weight);
    }

    public void setHeight(double height) {
        this.height = roundToTwoDecimalPlaces(height);
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

    public void setSick(boolean issick) {
        isSick = issick;
    }

    public void setDead(boolean isdead) {
        isDead = isdead;
    }

    // METHODS
    /**
     * round to two decimal places.
     *
     * @param value the value to convert
     */
    private double roundToTwoDecimalPlaces(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Eat.
     *
     * @param satietyPoint the satiety point
     */
    public void eat(int satietyPoint) {
        int newSatiety = this.getSatiety() + satietyPoint;
        this.setSatiety(newSatiety);
        this.setWeight(this.getWeight() + 0.5);

        if (this.getSatiety() <= 40) {
            this.setHungry(true);
        }

        if (this.getSatiety() <= 100) {
            this.setWeight(this.getWeight() + satietyPoint * 0.1);
        }

        if (this.getSatiety() > 100 && !this.isDead()) {
            int newHealth = this.getHealth() - 5;
            this.setHealth(newHealth);
            System.out.println("Attention "+this.getName() + " mange trop -5PV !");
            if (this.getHealth() <= 0) {
                if (this instanceof Reborner) {
                    System.out.println(this.getName() + " est mort à force de manger !");
                    ((Reborner) this).reborn();
                } else {
                    this.setDead(true);
                    this.setSatiety(0);
                    System.out.println(this.getName() + " est mort à force de manger !");
                }
            }
            this.setSatiety(100);
        }

        if (this.getWeight() < 0) {
            this.setWeight(0);
        }
        if (this.getHealth() < 0) {
            this.setHealth(0);
        }
    }

    /**
     * Make sound.
     *
     * @param soundUrl the sound url
     */
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

    /**
     * Healing.
     *
     * @param healingPoint the healing point
     */
    public void healing(int healingPoint) {
        this.setHealth(this.getHealth() + healingPoint);
        this.setHealth(Math.min(this.getHealth(), 100));
        this.isSick = false;
    }

    /**
     * Sleep.
     */
    public void sleep(){
        this.setAsleep(true);
    }

    /**
     * Wake up.
     */
    public void wakeUp() {
        this.setAsleep(false);
    }

    /**
     * Grow old.
     */
    public void growOld() {
        Random random = new Random();
        this.setAge(this.getAge() + 1);

        double weightIncrease = 1.0 + (2.0 - 0.5) * random.nextDouble();
        this.setWeight(this.getWeight() + weightIncrease);

        if (this.getAge() < 30) {
            double heightIncrease = 0.1 + (1.0 - 0.1) * random.nextDouble();
            this.setHeight(this.getHeight() + heightIncrease);
        }

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

    /**
     * Periodic update.
     *
     * @param enclosure the enclosure
     */
    public void periodicUpdate(Enclosure enclosure) {
        growOld();

        if (!isAsleep && RANDOM.nextInt(100) < 10) {
            sleep();
            System.out.println(enclosure.getName() + ": " + name + " s'est endormi.");
        } else if (isAsleep && RANDOM.nextInt(100) < 20) {
            wakeUp();
            System.out.println(enclosure.getName() + ": " + name + " s'est réveillé.");
        }

        int sicknessProbability = 3;
        if (enclosure.getCleanliness() < 30) {
            sicknessProbability = 10;
        }

        if (!isSick && RANDOM.nextInt(100) < sicknessProbability) {
            setSick(true);
            System.out.println(enclosure.getName() + ": " + name + " est tombé malade.");
        }

        if (isSick) {
            int newHealth = this.getHealth() - 1;
            setHealth(newHealth);
            if (this.getHealth() < 0) {
                setHealth(0);
            }
            System.out.println(enclosure.getName() + ": " + name + " est malade et perd 1 point de vie.");
            if (this.getHealth() == 0) {
                System.out.println(enclosure.getName() + ": " + name + " est mort de maladie.");
                if (this instanceof Reborner) {
                    ((Reborner) this).reborn();
                } else {
                    this.setDead(true);
                }
            }
        }

        int newSatiety = this.getSatiety() - 2;
        setSatiety(newSatiety);
        if (this.getSatiety() < 0) {
            setSatiety(0);
        }
        if (this.getSatiety() < 30) {
            int newHealth = this.getHealth() - 3;
            setHealth(newHealth);
            if (this.getHealth() < 0) {
                setHealth(0);
            }
            System.out.println(enclosure.getName() + ": " + name + " a très faim et perd 3 points de vie.");
            if (this.getHealth() == 0) {
                System.out.println(enclosure.getName() + ": " + name + " est mort de faim.");
                if (this instanceof Reborner) {
                    ((Reborner) this).reborn();
                } else {
                    this.setDead(true);
                }
            }
        }

        if (this instanceof Oviparous && RANDOM.nextInt(100) < 5) {
            ((Oviparous) this).layEgg(enclosure);
        } else if (this instanceof Viviparous && RANDOM.nextInt(100) < 5) {
            ((Viviparous) this).giveBirth(enclosure);
        }
    }

    /**
     * Generate random name string.
     *
     * @param names the names
     * @return the string
     */
    protected String generateRandomName(String[] names) {
        return names[RANDOM.nextInt(names.length)];
    }

    /**
     * Generate random gender char.
     *
     * @return the char
     */
    protected char generateRandomGender() {
        return RANDOM.nextBoolean() ? 'm' : 'f';
    }

    /**
     * Generate random weight double.
     *
     * @param min the min
     * @param max the max
     * @return the double
     */
    protected double generateRandomWeight(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    /**
     * Generate random height double.
     *
     * @param min the min
     * @param max the max
     * @return the double
     */
    protected double generateRandomHeight(double min, double max) {
        return min + (max - min) * RANDOM.nextDouble();
    }

    /**
     * Generate random age max int.
     *
     * @return the int
     */
    protected int generateRandomAgeMax() {
        Random random = new Random();
        return random.nextInt(131) + 20;
    }
}
