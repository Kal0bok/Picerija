package Pica;

import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Klients {
    String vards, uzvards, adrese, telefons;
    
    public Klients() {
        Random rand = new Random();
        String[] vardi = {"Janis", "Andris", "Juris", "Maris"};
        String[] uzvardi = {"Berzins", "Kalnins", "Ozols", "Zarins"};
        String[] adreses = {"Brivibas iela 10", "Rigas iela 5", "Saules iela 22"};
        
        this.vards = vardi[rand.nextInt(vardi.length)];
        this.uzvards = uzvardi[rand.nextInt(uzvardi.length)];
        this.adrese = adreses[rand.nextInt(adreses.length)];
        this.telefons = "2" + (1000000 + rand.nextInt(8999999));
    }

    public static JLabel createClientLabel() {
        URL pngUrl = Klients.class.getResource("/image/client.png"); 
        if (pngUrl != null) {
            ImageIcon icon = new ImageIcon(pngUrl);
            Image scaledImage = icon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        }
        return new JLabel("Client Image Missing");
    }

    public String generetPasutijumu() {
        Random rand = new Random();
        
        String[] picas = {"Margarita (8€)", "Peperoni (10€)", "Havajas (11€)"};
        String[] izmeri = {"25cm (+0€)", "30cm (+2€)", "50cm (+5€)"};
        String[] piedevas = {"Dubultais siers (+1.50€)", "Olīvas (+0.80€)", "Sēnes (+1.20€)"};
        String[] mērces = {"Ķiploku (0.50€)", "Asā (0.50€)", "Kečups (0.30€)"};

        return String.format(
            "Klients: %s %s, adrese: %s, tel: %s. " +
            "Es vēlos: %s, izmērs: %s, piedeva: %s и mērce: %s.",
            vards, uzvards, adrese, telefons,
            picas[rand.nextInt(picas.length)],
            izmeri[rand.nextInt(izmeri.length)],
            piedevas[rand.nextInt(piedevas.length)],
            mērces[rand.nextInt(mērces.length)]
        );
    }
}