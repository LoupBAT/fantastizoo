package fr.g4zoo.fantastizoo.models.enclosures;

import fr.g4zoo.fantastizoo.models.ZooMaster;

public class Aquarium extends Enclosure {
    private double depth;
    private double waterSalinity;

    /**
     * Instantiates a new Aquarium.
     *
     * @param name          the name
     * @param area          the area
     * @param depth         the depth
     * @param waterSalinity the water salinity
     */
// Constructor
    public Aquarium(String name, double area, double depth, double waterSalinity) {
        super(name, area);
        this.depth = depth;
        this.waterSalinity = waterSalinity;
    }

    // Getters et Setters pour depth et waterSalinity
    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getWaterSalinity() {
        return waterSalinity;
    }

    public void setWaterSalinity(double waterSalinity) {
        this.waterSalinity = waterSalinity;
    }

    @Override
    public boolean getCleaned(ZooMaster master) {
        boolean lifeLost = super.getCleaned(master);
        System.out.println("La vérification du volume d'eau a été faite.");
        return lifeLost;
    }
}
