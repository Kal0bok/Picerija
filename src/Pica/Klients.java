package Pica;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Klients {
    public String vards, uzvards, adrese, telefons;
    private Random rand = new Random();

    private static final List<String> vardi = new ArrayList<>(Arrays.asList(
        "Janis", "Andris", "Juris", "Maris", "Nikita", "Anna"
    ));
    
    private static final List<String> uzvardi = new ArrayList<>(Arrays.asList(
        "Berzins", "Kalnins", "Ozols", "Zarins", "Killer", "Lapiņš"
    ));
    
    private static final List<String> adreses = new ArrayList<>(Arrays.asList(
        "Brivibas iela 10", "Rigas iela 5", "Saules iela 22", "Liela iela 1"
    ));

    public Klients() {
        this.vards = vardi.get(rand.nextInt(vardi.size()));
        this.uzvards = uzvardi.get(rand.nextInt(uzvardi.size()));
        this.adrese = adreses.get(rand.nextInt(adreses.size()));
        this.telefons = "2" + (1000000 + rand.nextInt(8999999));
    }

    public static JLabel createClientLabel() {
        URL pngUrl = Klients.class.getResource("/image/cashier.png"); 
        if (pngUrl != null) {
            ImageIcon icon = new ImageIcon(pngUrl);
            Image scaledImage = icon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        }
        return new JLabel("Client Image Missing");
    }

    public String getPilnsVards() {
        return vards + " " + uzvards;
    }

    public static void pievienotVardu(String jaunsVards) {
    	vardi.add(jaunsVards);
    }
}