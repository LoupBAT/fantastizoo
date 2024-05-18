package fr.g4zoo.fantastizoo.models.enclosures;

public class Aquarium extends Enclosure {
    private double depth;
    private double waterSalinity;

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

    // Overide to include the depth and waterSalinity
    @Override
    public void displayInformations() {
        super.displayInformations();
        System.out.println("Profondeur : " + depth + " m");
        System.out.println("Salinité de l'eau : " + waterSalinity + " ‰");
    }

    @Override
    public void getCleaned() {
        super.getCleaned();
        System.out.println("La vérification de la profondeur du bassin et la salinité de l'eau a été faite.");
    }
}
