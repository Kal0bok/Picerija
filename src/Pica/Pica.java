package Pica;

import java.util.ArrayList;

public class Pica {
    protected String nosaukums;
    protected String izmers; 
    protected double pamataCena;
    protected ArrayList<String> piedevas = new ArrayList<>();

    public Pica(String nosaukums, String izmers, double pamataCena) {
        this.nosaukums = nosaukums;
        this.izmers = izmers;
        this.pamataCena = pamataCena;
    }

    public double aprekinatCenu() {
        double total = pamataCena;
        if (izmers.equals("Vidēja")) total += 2.0;
        if (izmers.equals("Liela")) total += 4.0;
        total += piedevas.size() * 0.50;
        return total;
    }

    public void pievienotPiedevu(String piedeva) {
        this.piedevas.add(piedeva);
    }
    
    public String dabutInformaciju() {
        return nosaukums + " (" + izmers + "), Piedevas: " + piedevas.toString();
    }
}