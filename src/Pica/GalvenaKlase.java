package Pica;

import javax.swing.*;

public class GalvenaKlase {

    public static void atvertProgrammu(JFrame frame) {
        String izvele;
        do {
            izvele = JOptionPane.showInputDialog(null, "1 - Pienemt pasutijumu\n0 - Iziet");
            if (izvele != null && izvele.equals("1")) {
                apkalpotKlientu();
            }
        } while (izvele != null && !izvele.equals("0"));
    }

    private static void apkalpotKlientu() {
        String[] opcijas = {"Uz vietas", "Zvans"};
        int tipsIndex = JOptionPane.showOptionDialog(null, "Pasūtījuma veids:", "R-keeper",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcijas, opcijas[0]);

        if (tipsIndex == -1) return;
        String tips = opcijas[tipsIndex];
        
        if (tips.equals("Uz vietas")) {
            GUI.piegKlients1(true);
        } else {
            GUI.piegKlients2(true);
        }

        Klients k = new Klients();
        Pica p = new Pica();

        processIevads("Labdien! Mani sauc " + k.getPilnsVards(), k.getPilnsVards(), "Ierakstiet klienta vārdu un uzvārdu:");

        processIevads("Mans numurs ir " + k.telefons, k.telefons, "Ierakstiet tel. numuru:");

        processIevads("Es vēlētos picu: " + p.izveletaPica, p.izveletaPica, "Kādu picu klients vēlas?");

        processIevads("Izmērs būs " + p.izveletsIzmers, p.izveletsIzmers, "Ierakstiet picas izmēru:");

        processIevads("Mērci man, lūdzu, " + p.izveletaMerce, p.izveletaMerce, "Kādu mērci klients izvēlējās?");

        if (!p.izveletaUzkoda.equals("Nekas")) {
            processIevads("Vēlētos arī uzkodu: " + p.izveletaUzkoda, p.izveletaUzkoda, "Kādu uzkodu pievienot?");
        }

        if (!p.izveletsDzeriens.equals("Nekas")) {
            processIevads("Dzert gribēšu: " + p.izveletsDzeriens, p.izveletsDzeriens, "Kādu dzērienu pievienot?");
        }

        if (tips.equals("Zvans")) {
            processIevads("Mana adrese ir " + k.adrese, k.adrese, "Ierakstiet piegādes adresi:");
            p.kopejaCena += 4.50;
            JOptionPane.showMessageDialog(null, "Piegādes maksa +4.50€ pievienota.");
        }

        JOptionPane.showMessageDialog(null, "Pasūtījums pieņemts!\nKopā apmaksai: " + String.format("%.2f", p.kopejaCena) + "€");
    }

    private static void processIevads(String klientaTeiktais, String pareizi, String lauks) {
        while (true) {
            JOptionPane.showMessageDialog(null, klientaTeiktais, "Klients runā", JOptionPane.PLAIN_MESSAGE);
            String ievads = JOptionPane.showInputDialog(null, lauks);

            if (ievads == null) {
                JOptionPane.showMessageDialog(null, "Jūs atteicāties apkalpot klientu!\nSODS: 1 000 000 €", "Sods", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            if (ievads.equalsIgnoreCase(pareizi)) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Nepareizi! Klients atkārto vēlreiz...", "Kļūda", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}