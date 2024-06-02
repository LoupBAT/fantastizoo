package fr.g4zoo.fantastizoo.models.enclosures;

import fr.g4zoo.fantastizoo.models.ZooMaster;
/**
 * The type Aviary.
 */
public class Aviary extends Enclosure {
    private double height;

    /**
     * Instantiates a new Aviary.
     *
     * @param name   the name
     * @param area   the area
     * @param height the height
     */
    // Constructor
    public Aviary(String name, double area, double height) {
        super(name, area);
        this.height = height;
    }

    // Getter / Setter
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean getCleaned(ZooMaster master) {
        boolean lifeLost = super.getCleaned(master);
        System.out.println("La vérification du toit de la cage a été faite.");
        return lifeLost;
    }
}
