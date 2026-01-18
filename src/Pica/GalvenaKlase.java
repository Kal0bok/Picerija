package Pica;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class GalvenaKlase {
    private static Queue<String> pasutijumuRinda = new LinkedList<>();
    private static double kase = 0;

    public static void atvertProgrammu(JFrame frame) {
        String[] menuOpcijas = {
            "1 - Pieņemt pasūtījumu", 
            "2 - Aktīvie pasūtījumi", 
            "3 - Izsniegt pasūtījumu (Apmaksa)", 
            "0 - Iziet"
        };
        
        String izvele;
        do {
            izvele = (String) JOptionPane.showInputDialog(null, 
                "Rindā: " + pasutijumuRinda.size() + " | Kase: " + String.format("%.2f", kase) + "€", 
                "R-Keeper System", JOptionPane.PLAIN_MESSAGE, null, menuOpcijas, menuOpcijas[0]);

            if (izvele != null) {
                if (izvele.startsWith("1")) apkalpotKlientu();
                if (izvele.startsWith("2")) paraditAktivosPasutijumus();
                if (izvele.startsWith("3")) pabeigtPasutijumu();
            }
        } while (izvele != null && !izvele.startsWith("0"));
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
        processIevads("Izmērs: " + p.izveletsIzmers, p.izveletsIzmers, "Ierakstiet picas izmēru:");
        processIevads("Mērce: " + p.izveletaMerce, p.izveletaMerce, "Kādu mērci?");

        if (!p.izveletaUzkoda.equals("Nekas")) {
            processIevads("Uzkoda: " + p.izveletaUzkoda, p.izveletaUzkoda, "Kādu uzkodu?");
        }

        if (tips.equals("Zvans")) {
            processIevads("Mana adrese ir " + k.adrese, k.adrese, "Ierakstiet piegādes adresi:");
            p.kopejaCena += 4.50;
            JOptionPane.showMessageDialog(null, "Piegādes maksa +4.50€ pievienota.");
            GUI.piegKlients2(false);
        } else {
            GUI.piegKlients1(false);
        }

        String info = "KLIENTS: " + k.getPilnsVards() + "\n" +
                      "TIPS: " + tips + "\n" +
                      "PASŪTĪJUMS: " + p.izveletaPica + " (" + p.izveletsIzmers + ")\n" +
                      "MĒRCE: " + p.izveletaMerce + " | UZKODA: " + p.izveletaUzkoda + "\n" +
                      "SUMMA: " + String.format("%.2f", p.kopejaCena) + "€";
        
        pasutijumuRinda.add(info);
        JOptionPane.showMessageDialog(null, "Pasūtījums veiksmīgi pievienots rindai!");
    }

    private static void paraditAktivosPasutijumus() {
        if (pasutijumuRinda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pašlaik nav aktīvu pasūtījumu!", "Informācija", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String str = "AKTĪVIE PASŪTĪJUMI: " + pasutijumuRinda.size() + 
                     "\n=================================\n\n";

        int i = 1;
        for (String pasutijums : pasutijumuRinda) {
            str += i + ". PASŪTĪJUMS:\n" + pasutijums + "\n";
            str += "---------------------------------\n";
            i++;
        }

        JTextArea ta = new JTextArea(str, 15, 45);
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        JScrollPane sp = new JScrollPane(ta);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null, sp, "Aktīvo pasūtījumu saraksts", JOptionPane.PLAIN_MESSAGE);
    }

    private static void pabeigtPasutijumu() {
        if (pasutijumuRinda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena pasūtījuma, ko izsniegt!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String pasutijums = pasutijumuRinda.poll();
        
        try {
            String[] lines = pasutijums.split("\n");
            String priceLine = lines[lines.length - 1]; 
            String priceVal = priceLine.replaceAll("[^0-9,.]", "").replace(",", ".");
            kase += Double.parseDouble(priceVal);
        } catch (Exception e) {
            System.out.println("Kļūda aprēķinot kases summu");
        }

        JOptionPane.showMessageDialog(null, "Pasūtījums izsniegts!\nNauda saņemta kase.");
    }

    private static void processIevads(String klientaTeiktais, String pareizi, String lauks) {
        while (true) {
            JOptionPane.showMessageDialog(null, klientaTeiktais, "Klients runā", JOptionPane.PLAIN_MESSAGE);
            String ievads = JOptionPane.showInputDialog(null, lauks);

            if (ievads == null) {
                JOptionPane.showMessageDialog(null, "Darbinieks pameta darbu!\nSPĒLES BEIGAS");
                System.exit(0);
            }

            if (ievads.equalsIgnoreCase(pareizi)) break;
            JOptionPane.showMessageDialog(null, "Kļūda! Klients dusmīgi atkārto...");
        }
    }
}