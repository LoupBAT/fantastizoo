package fr.g4zoo.fantastizoo.models;

public class ZooMaster {

    private String name;
    private Character gender;
    private int age;
    private int hp = 3;
    private int action =5;

    /**
     * Instantiates a new Zoo master.
     *
     * @param name   the name
     * @param gender the gender
     * @param age    the age
     */
    // Constructor
    public ZooMaster(String name, Character gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // GETTERS

    public String getName() {
        return name;
    }

    public Character getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getHp() {
        return hp;
    }

    public int getAction() {
        return action;
    }

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public void setAction(Integer action) {
        this.action = action;
    }
}
