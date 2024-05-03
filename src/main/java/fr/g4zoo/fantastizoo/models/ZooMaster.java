package fr.g4zoo.fantastizoo.models;

public class ZooMaster {

    private String name;
    private Character gender;
    private Integer age;
    private Integer hp;
    private Integer action;

    //GETTERS

    public String getName() {
        return name;
    }

    public Character getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAction() {
        return action;
    }

    //SETTERS

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