package Pica;

public class Pica {
    private String nosaukums;
    private String izmers;
    private String piedevas;
    private double cena;

    public Pica(String nosaukums, String izmers, String piedevas, double cena) {
        this.nosaukums = nosaukums;
        this.izmers = izmers;
        this.piedevas = piedevas;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) | Piedevas: %s | Cena: %.2f€", 
                nosaukums, izmers, piedevas, cena);
    }
}