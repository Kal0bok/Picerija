package Pica;

import java.awt.Font;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class GalvenaKlase {
    private static Queue<String> pasutijumuRinda = new LinkedList<>();
    private static double kase = 0;
    private static boolean irPartraukums = false;
    private static final String FAILA_NOSAUKUMS = "pasutijumi.txt";

    public static void atvertProgrammu(JFrame frame) {
        String izvele;
        do {
            String status = irPartraukums ? " [PĀRTRAUKUMS] " : " [DARBĀ] ";
            
            String[] menuOpcijas = {
                "1 - Pieņemt pasūtījumu", 
                "2 - Aktīvie pasūtījumi", 
                "3 - Apkalpot klientu",
                "4 - Pasūtījumu vēsture", 
                "5 - Pārtraukums",
                "0 - Nodot maiņu"
            };
            
            izvele = (String) JOptionPane.showInputDialog(null, 
                "Statuss:" + status + "| Rindā: " + pasutijumuRinda.size() + " | Kase: " + String.format("%.2f", kase) + "€", 
                "R-Keeper System", JOptionPane.PLAIN_MESSAGE, null, menuOpcijas, menuOpcijas[0]);

            if (izvele != null) {
                switch (izvele.charAt(0)) {
                    case '1':
                        if (irPartraukums) {
                            JOptionPane.showMessageDialog(null, "Jūs nevarat apkalpot klientus, kamēr esat pārtraukumā!", "Pārtraukums", JOptionPane.WARNING_MESSAGE);
                        } else {
                            apkalpotKlientu();
                        }
                        break;
                    case '2':
                        paraditAktivosPasutijumus();
                        break;
                    case '3':
                        pabeigtPasutijumu();
                        break;
                    case '4':
                        paraditVesturi();
                        break;
                    case '5':
                        irPartraukums = !irPartraukums;
                        JOptionPane.showMessageDialog(null, irPartraukums ? "Pārtraukums sākās!" : "Pārtraukums beidzās!");
                        break;
                    case '0':
                        JOptionPane.showMessageDialog(null, "Maiņa nodota. Atgriešanās sākuma ekrānā.");
                        frame.setContentPane(GUI.createMainMenu(frame));
                        frame.revalidate();
                        frame.repaint(); 
                }
            } else {
                return;
            }
        } while (izvele != null && !izvele.startsWith("0"));
    }

    private static void apkalpotKlientu() {
        String[] opcijas = {"Uz vietas", "Zvans"};
        int tipsIndex = JOptionPane.showOptionDialog(null, "Pasūtījuma veids:", "R-keeper",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcijas, opcijas[0]);

        if (tipsIndex == -1) return;
        
        String tips = opcijas[tipsIndex];
        Klients k = new Klients();
        Pica p = new Pica();

        if (tipsIndex == 0) {
            GUI.piegKlients1(true);
            if (!processIevads("Labdien! Mani sauc " + k.getPilnsVards(), k.getPilnsVards(), "Ierakstiet vārdu:")) { GUI.piegKlients1(false); return; }
            
            if (!processIevads("Es vēlētos picu: " + p.izveletaPica, p.getOnlyName(p.izveletaPica), "Kādu picu klients vēlas?")) { GUI.piegKlients1(false); return; }
            if (!processIevads("Izmērs: " + p.izveletsIzmers, p.getOnlyName(p.izveletsIzmers), "Ierakstiet picas izmēru:")) { GUI.piegKlients1(false); return; }
            if (!processIevads("Mērce: " + p.izveletaMerce, p.getOnlyName(p.izveletaMerce), "Kādu mērci?")) { GUI.piegKlients1(false); return; }
            
            if (!p.izveletaPiedeva.equals("Nekas")) {
                if (!processIevads("Papildus vēlos: " + p.izveletaPiedeva, p.getOnlyName(p.izveletaPiedeva), "Kādu piedevu?")) { GUI.piegKlients1(false); return; }
            }
            if (!p.izveletaUzkoda.equals("Nekas")) {
                if (!processIevads("Uzkoda: " + p.izveletaUzkoda, p.getOnlyName(p.izveletaUzkoda), "Kādu uzkodu?")) { GUI.piegKlients1(false); return; }
            }
            if (!p.izveletsDzeriens.equals("Nekas")) {
                if (!processIevads("Dzēriens: " + p.izveletsDzeriens, p.getOnlyName(p.izveletsDzeriens), "Kādu dzērienu?")) { GUI.piegKlients1(false); return; }
            }
            GUI.piegKlients1(false);
        } else {
            GUI.piegKlients2(true);
            if (!processIevads("Labdien! Mani sauc " + k.getPilnsVards(), k.getPilnsVards(), "Ierakstiet vārdu:")) { GUI.piegKlients2(false); return; }
            if (!processIevads("Mans numurs ir " + k.telefons, k.telefons, "Ierakstiet tel. numuru:")) { GUI.piegKlients2(false); return; }
            
            if (!processIevads("Es vēlētos picu: " + p.izveletaPica, p.getOnlyName(p.izveletaPica), "Kādu picu?")) { GUI.piegKlients2(false); return; }
            if (!processIevads("Izmērs: " + p.izveletsIzmers, p.getOnlyName(p.izveletsIzmers), "Izmērs?")) { GUI.piegKlients2(false); return; }
            if (!processIevads("Mērce: " + p.izveletaMerce, p.getOnlyName(p.izveletaMerce), "Mērce?")) { GUI.piegKlients2(false); return; }
            
            if (!p.izveletaPiedeva.equals("Nekas")) {
                if (!processIevads("Papildus vēlos: " + p.izveletaPiedeva, p.getOnlyName(p.izveletaPiedeva), "Piedeva?")) { GUI.piegKlients2(false); return; }
            }
            if (!p.izveletaUzkoda.equals("Nekas")) {
                if (!processIevads("Uzkoda: " + p.izveletaUzkoda, p.getOnlyName(p.izveletaUzkoda), "Uzkoda?")) { GUI.piegKlients2(false); return; }
            }
            if (!p.izveletsDzeriens.equals("Nekas")) {
                if (!processIevads("Dzēriens: " + p.izveletsDzeriens, p.getOnlyName(p.izveletsDzeriens), "Dzēriens?")) { GUI.piegKlients2(false); return; }
            }
            if (!processIevads("Mana adrese ir " + k.adrese, k.adrese, "Ierakstiet piegādes adresi:")) { GUI.piegKlients2(false); return; }
            
            p.kopejaCena += 4.50;
            JOptionPane.showMessageDialog(null, "Piegādes maksa +4.50€ pievienota.");
            GUI.piegKlients2(false);
        }

        String info = "KLIENTS: " + k.getPilnsVards() + "\n" +
                      "TIPS: " + tips + "\n" +
                      "PASŪTĪJUMS: " + p.izveletaPica + " (" + p.izveletsIzmers + ")\n" +
                      "MĒRCE: " + p.izveletaMerce + " | PIEDEVA: " + p.izveletaPiedeva + "\n" +
                      "UZKODA: " + p.izveletaUzkoda + " | DZĒRIENS: " + p.izveletsDzeriens + "\n" +
                      "SUMMA: " + String.format("%.2f", p.kopejaCena).replace(",", ".") + "€";
        
        pasutijumuRinda.add(info);
        JOptionPane.showMessageDialog(null, "Pasūtījums pievienots rindai!");
    }

    private static boolean processIevads(String klientaTeiktais, String pareizi, String lauks) {
        while (true) {
            JOptionPane.showMessageDialog(null, klientaTeiktais, "Klients runā", JOptionPane.PLAIN_MESSAGE);
            String ievads = JOptionPane.showInputDialog(null, lauks);
            
            if (ievads == null) {
                JOptionPane.showMessageDialog(null, "Jūs atteicāties apkalpot klientu!\nSODS: 1 000 000 €", "SODS", JOptionPane.ERROR_MESSAGE);
                return false; 
            }
            
            if (ievads.trim().equalsIgnoreCase(pareizi.trim())) {
                return true;
            }
            
            JOptionPane.showMessageDialog(null, "Nepareizi! Klients gaidīja: '" + pareizi + "'\nAtkārtojiet...");
        }
    }

    private static void paraditAktivosPasutijumus() {
        if (pasutijumuRinda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav aktīvu pasūtījumu!");
            return;
        }
        StringBuilder str = new StringBuilder("AKTĪVIE PASŪTĪJUMI:\n\n");
        int i = 1;
        for (String p : pasutijumuRinda) {
            str.append(i++).append(". ").append(p).append("\n------------------\n");
        }
        raditTekstaLogu(str.toString(), "Aktīvie pasūtījumi");
    }

    private static void pabeigtPasutijumu() {
        if (pasutijumuRinda.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Rinda ir tukša!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String pasutijums = pasutijumuRinda.poll();
        SaglabatFaila.saglabat(FAILA_NOSAUKUMS, pasutijums);
        
        try {
            String[] rindas = pasutijums.split("\n");
            String cenasRinda = rindas[rindas.length - 1]; 
            String cenasVertiba = cenasRinda.replaceAll("[^0-9,.]", "").replace(",", ".");
            kase += Double.parseDouble(cenasVertiba);
        } catch (Exception e) {
            System.out.println("Kļūda aprēķinot kasei.");
        }

        JOptionPane.showMessageDialog(null, "Pasūtījums pabeigts!");
    }
    
    private static void paraditVesturi() {
        String failaSaturss = SaglabatFaila.nolasit(FAILA_NOSAUKUMS);
        raditTekstaLogu(failaSaturss, "Pasūtījumu vēsture (no faila)");
    }

    private static void raditTekstaLogu(String saturs, String virsraksts) {
        JTextArea ta = new JTextArea(saturs, 20, 50);
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        JScrollPane sp = new JScrollPane(ta);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(null, sp, virsraksts, JOptionPane.PLAIN_MESSAGE);
    }
}