package Pica;

import javax.swing.*;
import java.util.Random;

public class GalvenaKlase {

    private static Random rand = new Random();

    private static final String[] VĀRDI = {"Nikita", "Artjoms", "Jānis", "Maksims", "Anna", "Marija", "Kristaps", "Laura"};
    private static final String[] UZVĀRDI = {"Killer", "Bērziņš", "Ozols", "Zariņš", "Lapiņš", "Sokolovs", "Kļaviņš", "Vītols"};
    private static final String[] PICAS = {"Margarita", "Studentu", "Havajas", "Kapri", "Lauku", "Vegāniskā", "Salami", "Meksikāņu"};
    private static final String[] UZKODAS = {"Frī kartupeļi", "Sīpolu gredzeni", "Ķiploku grauzdiņi", "Mērce", "Nekas papildus"};

    public static void atvertProgrammu(JFrame frame) {
        String izvele;
        do {
            izvele = JOptionPane.showInputDialog(null, 
                "1 - Pieņemt nākamo pasūtījumu\n" +
                "0 - Atgriezties uz galveno izvēlni", 
                "R-keeper (Aktīva maiņa)", JOptionPane.QUESTION_MESSAGE);
            
            if (izvele == null) break;

            if (izvele.equals("1")) {
                apkalpotKlientu();
            }
        } while (!izvele.equals("0"));
    }

    private static void apkalpotKlientu() {
        String[] opcijas = {"Uz vietas", "Zvans"};
        int tipsIndex = JOptionPane.showOptionDialog(null, "Kā klients pasūta?", 
                "Jauns pasūtījums", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcijas, opcijas[0]);
        
        if (tipsIndex == -1) return;
        String tips = opcijas[tipsIndex];

        String vards = VĀRDI[rand.nextInt(VĀRDI.length)];
        String uzvards = UZVĀRDI[rand.nextInt(UZVĀRDI.length)];
        String pilnsVards = vards + " " + uzvards;
        String numurs = "2" + (1000000 + rand.nextInt(8999999));
        String pica = PICAS[rand.nextInt(PICAS.length)];
        String uzkoda = UZKODAS[rand.nextInt(UZKODAS.length)];

        processIevads("Labdien! Mani sauc " + pilnsVards + ".", 
                      pilnsVards, 
                      "Ievadiet klienta Vārdu un Uzvārdu:");

        processIevads("Mans telefona numurs ir: " + numurs + ".", 
                      numurs, 
                      "Ievadiet klienta telefona numuru:");

        processIevads("Es vēlētos pasūtīt picu: " + pica + ".", 
                      pica, 
                      "Kādu picu klients vēlas?");

        processIevads("Pie pasūtījuma man, lūdzu, vēl: " + uzkoda + ".", 
                      uzkoda, 
                      "Kādu papildus uzkodu klients izvēlējās?");

        double cena = 12.0; 
        if (tips.equals("Zvans")) {
            String adrese = "Rīgas iela " + (rand.nextInt(100) + 1);
            processIevads("Mana adrese piegādei ir: " + adrese + ".", 
                          adrese, 
                          "Ievadiet piegādes adresi:");
            cena += 5.0; 
        }

        JOptionPane.showMessageDialog(null, "Pasūtījums veiksmīgi noformēts!\nKlients: " + pilnsVards + 
            "\nKopējā summa: " + String.format("%.2f", cena) + "€");
    }

    private static void processIevads(String koSakaKlient, String pareizaAtbilde, String ievadesLauks) {
        String lietotajaIevads = "";
        
        while (true) {
            JOptionPane.showMessageDialog(null, koSakaKlient, "Klients runā...", JOptionPane.PLAIN_MESSAGE);
            lietotajaIevads = JOptionPane.showInputDialog(null, ievadesLauks);

            if (lietotajaIevads == null) {
                JOptionPane.showMessageDialog(null, 
                    "Jūs atteicāties apkalpot klientu!\nSODS: 1 000 000 €", 
                    "PĀRKĀPUMS", JOptionPane.ERROR_MESSAGE);
                System.exit(0); 
            }

            if (lietotajaIevads.equalsIgnoreCase(pareizaAtbilde)) {
                break; 
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Nepareizi! Klients atkārto vēlreiz...", 
                    "Kļūda", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}