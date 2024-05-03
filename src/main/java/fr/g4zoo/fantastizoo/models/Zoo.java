package fr.g4zoo.fantastizoo.models;

public class Zoo {

    private String name;
    private final Integer maxEnclosures = 24;

    //GETTERS

    public String getName() {
        return name;
    }

    public Integer getMaxEnclosures() {
        return maxEnclosures;
    }

    //SETTERS

    public void setName(String name) {
        this.name = name;
    }
    
}
