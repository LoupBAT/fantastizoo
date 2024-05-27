package fr.g4zoo.fantastizoo.models.enclosures;

public class Aviary extends Enclosure {
    private double height;

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

    // Overide to include the height
    @Override
    public void displayInformations() {
        super.displayInformations();
        System.out.println("Hauteur : " + height + " m");
    }

    @Override
    public void getCleaned() {
        super.getCleaned();
        System.out.println("La vérification du toit de la cage a été faite.");
    }
}
