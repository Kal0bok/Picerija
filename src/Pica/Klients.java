package Pica;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Klients {
    public String vards, uzvards, adrese, telefons;
    private Random rand = new Random();

    private String[] vardi = {"Janis", "Andris", "Juris", "Maris", "Nikita", "Anna"};
    private String[] uzvardi = {"Berzins", "Kalnins", "Ozols", "Zarins", "Killer", "Lapiņš"};
    private String[] adreses = {"Brivibas iela 10", "Rigas iela 5", "Saules iela 22", "Liela iela 1"};

    public Klients() {
        this.vards = vardi[rand.nextInt(vardi.length)];
        this.uzvards = uzvardi[rand.nextInt(uzvardi.length)];
        this.adrese = adreses[rand.nextInt(adreses.length)];
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
}