package Pica;

import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class Metodes {
    
    public static String virknesParbaude(String jautajums, String noklusejums) {
        String ievade;
        do {
            ievade = JOptionPane.showInputDialog(null, jautajums, noklusejums);
            if (ievade == null) return null;
            ievade = ievade.trim();
            if (Pattern.matches("^[\\p{L} ]+$", ievade)) {
                return ievade;
            } else {
                JOptionPane.showMessageDialog(null, "Lūdzu, izmantojiet tikai burtus!");
            }
        } while (true);
    }

    public static String telefonaParbaude() {
        String numurs;
        do {
            numurs = JOptionPane.showInputDialog("Ievadiet telefona numuru (8 cipari):");
            if (numurs == null) return null;
            if (Pattern.matches("^\\d{8}$", numurs)) {
                return numurs;
            } else {
                JOptionPane.showMessageDialog(null, "Kļūda! Jāievada tieši 8 cipari.");
            }
        } while (true);
    }
}