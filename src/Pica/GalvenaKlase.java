package Pica;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    	

    	
    	
    
