package Pica;

import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GalvenaKlase {
    private static Queue<String> pasutijumuRinda = new LinkedList<>();
    private static ArrayList<String> vesture = new ArrayList<>();
    private static double kase = 0;
    
    private static boolean irPārtraukums = false;

    public static void atvertProgrammu(JFrame frame) {
        String izvele;
        do {
            String status = irPārtraukums ? " [PĀRTAUKUMS] " : " [DARBĀ] ";
            
            String[] menuOpcijas = {
                "1 - Pieņemt pasūtījumu", 
                "2 - Aktīvie pasūtījumi", 
                "3 - Apkalpot klientu (Apmaksa)",
                "4 - Pasūtījumu vēsture",
                "5 - Pārtraukums",
                "0 - Iziet"
            };
            
            izvele = (String) JOptionPane.showInputDialog(null, 
                "Statuss:" + status + "| Rindā: " + pasutijumuRinda.size() + " | Kase: " + String.format("%.2f", kase) + "€", 
                "R-Keeper System", JOptionPane.PLAIN_MESSAGE, null, menuOpcijas, menuOpcijas[0]);

            if (izvele != null) {
                if (izvele.startsWith("1")) {
                    if (irPārtraukums) {
                        JOptionPane.showMessageDialog(null, "Jūs nevarat apkalpot klientus, kamēr esat pārtraukumā!", "Pārtraukums", JOptionPane.WARNING_MESSAGE);
                    } else {
                        apkalpotKlientu();
                    }
                }
                
                if (izvele.startsWith("2")) paraditAktivosPasutijumus();
                if (izvele.startsWith("3")) pabeigtPasutijumu();
                if (izvele.startsWith("4")) paraditVesturi();
                if (izvele.startsWith("5")) {
                    irPārtraukums = !irPārtraukums;
                    String zinojums = irPārtraukums ? "Pārtraukums sākās!" : "Pārtraukums beidzās! Atpakaļ pie darba.";
                    JOptionPane.showMessageDialog(null, zinojums);
                }
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
                      "SUMMA: " + String.format("%.2f", p.kopejaCena).replace(",", ".") + "€";
        
        pasutijumuRinda.add(info);
        JOptionPane.showMessageDialog(null, "Pasūtījums veiksmīgi pievienots rindai!");
    }

    private static void paraditAktivosPasutijumus() {
        if (pasutijumuRinda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pašlaik nav aktīvu pasūtījumu!", "Informācija", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder str = new StringBuilder("AKTĪVIE PASŪTĪJUMI: " + pasutijumuRinda.size() + 
                     "\n=================================\n\n");

        int i = 1;
        for (String pasutijums : pasutijumuRinda) {
            str.append(i).append(". PASŪTĪJUMS:\n").append(pasutijums).append("\n");
            str.append("---------------------------------\n");
            i++;
        }

        JTextArea ta = new JTextArea(str.toString(), 15, 45);
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
        vesture.add(pasutijums);
        
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
    
    private static void paraditVesturi() {
        if (vesture.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vēsture ir tukša!", "Informācija", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("PABEIGTIE PASŪTĪJUMI: ").append(vesture.size())
          .append("\n=================================\n\n");

        for (int i = 0; i < vesture.size(); i++) {
            sb.append(i + 1).append(". PASŪTĪJUMS (PABEIGTS):\n").append(vesture.get(i)).append("\n");
            sb.append("---------------------------------\n");
        }

        JTextArea ta = new JTextArea(sb.toString(), 15, 45);
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        JScrollPane sp = new JScrollPane(ta);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null, sp, "Pasūtījumu vēsture", JOptionPane.PLAIN_MESSAGE);
    }

    private static void processIevads(String klientaTeiktais, String pareizi, String lauks) {
        while (true) {
            JOptionPane.showMessageDialog(null, klientaTeiktais, "Klients runā", JOptionPane.PLAIN_MESSAGE);
            String ievads = JOptionPane.showInputDialog(null, lauks);

            if (ievads == null) {
                JOptionPane.showMessageDialog(null, "Jūs atteicāties apkalpot klientu!\nSODS: 1 000 000 €");
                System.exit(0);
            }

            if (ievads.trim().equalsIgnoreCase(pareizi.trim())) break;
            JOptionPane.showMessageDialog(null, "Nepareizi! Klients atkārto vēlreiz...");
        }
    }
}