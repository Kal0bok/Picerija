package Pica;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GalvenaKlase {	
	
	private static Queue<String> pasutRinda = new LinkedList<>();
    private static double kase = 0;

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

        processIevads("Labdien! Mani sauc " + k.getPilnsVards(), k.getPilnsVards(), "Ierakstiet vārdu:");

        if (tips.equals("Zvans")) {
            processIevads("Mans numurs ir " + k.telefons, k.telefons, "Ierakstiet tel. numuru:");
        }

        processIevads("Es vēlētos picu: " + p.izveletaPica, p.izveletaPica, "Kādu picu klients vēlas?");
        processIevads("Izmērs būs " + p.izveletsIzmers, p.izveletsIzmers, "Ierakstiet picas izmēru:");
        processIevads("Mērci man, lūdzu " + p.izveletaMerce, p.izveletaMerce, "Kādu mērci?");

        if (!p.izveletaUzkoda.equals("Nekas")) {
            processIevads("Vēlos arī uzkodu: " + p.izveletaUzkoda, p.izveletaUzkoda, "Kādu uzkodu?");
        }

        if (tips.equals("Zvans")) {
            processIevads("Mana adrese ir " + k.adrese, k.adrese, "Ierakstiet piegādes adresi:");
            p.kopejaCena += 4.50;
            JOptionPane.showMessageDialog(null, "Piegādes maksa +4.50€ pievienota.");
            GUI.piegKlients2(false);
        } else {
            GUI.piegKlients1(false);
        }
        
        String pasutijumaInfo = k.getPilnsVards() + " (" + p.izveletaPica + ") - " + String.format("%.2f", p.kopejaCena);
        pasutijumuRinda.add(pasutijumaInfo);      

        JOptionPane.showMessageDialog(null, "Pasūtījums pieņemts!\nKopā apmaksai: " + String.format("%.2f", p.kopejaCena) + "€");
    }

    private static void processIevads(String klientaTeiktais, String pareizi, String lauks) {
        while (true) {
            JOptionPane.showMessageDialog(null, klientaTeiktais, "Klients runā", JOptionPane.PLAIN_MESSAGE);
            String ievads = JOptionPane.showInputDialog(null, lauks);

            if (ievads == null) {
                JOptionPane.showMessageDialog(null, "Jūs atteicāties apkalpot klientu!\nSODS: 1 000 000 €");
                System.exit(0);
            }

            if (ievads.equalsIgnoreCase(pareizi)) break;
            JOptionPane.showMessageDialog(null, "Nepareizi! Klients atkārto vēlreiz...");
        }
    }
}