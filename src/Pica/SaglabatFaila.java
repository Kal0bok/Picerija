package Pica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SaglabatFaila {

    public static void saglabat(String failaNosaukums, String dati) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(failaNosaukums, true))) {
            bw.write(dati);
            bw.newLine();
            bw.write("---------------------------------");
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Kļūda saglabājot failā: " + e.getMessage());
        }
    }

    public static String nolasit(String failaNosaukums) {
        StringBuilder saturs = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(failaNosaukums))) {
            String rinda;
            while ((rinda = br.readLine()) != null) {
                saturs.append(rinda).append("\n");
            }
        } catch (IOException e) {
            return "Fails " + failaNosaukums + " vēl neeksistē vai ir tukšs.";
        }
        return saturs.toString();
    }
}