package Pica;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Pica {
    private Random rand = new Random();

    public static final Map<String, Double> picas = new HashMap<>();
    public static final Map<String, Double> izmeri = new HashMap<>();
    public static final Map<String, Double> uzkodas = new HashMap<>();
    public static final Map<String, Double> merces = new HashMap<>(); 
    public static final Map<String, Double> dzerieni = new HashMap<>(); 

    static {
        picas.put("Margarita", 8.0);
        picas.put("Peperoni", 10.0);
        picas.put("Havajas", 11.0);
        picas.put("Studentu", 7.5);

        izmeri.put("25cm", 0.0);
        izmeri.put("30cm", 2.0);
        izmeri.put("50cm", 5.0);

        uzkodas.put("Fri", 2.50);
        uzkodas.put("Sipolu gredzeni", 3.00);
        uzkodas.put("Nekas", 0.0);

        merces.put("Kiploku", 0.50);
        merces.put("Asa", 0.50);
        merces.put("Kecups", 0.50);

        dzerieni.put("Kola", 1.50);
        dzerieni.put("Udens", 1.00);
        dzerieni.put("Nekas", 0.0);
    }

    public String izveletaPica, izveletsIzmers, izveletaUzkoda, izveletaMerce, izveletsDzeriens;
    public double kopejaCena = 0;

    public Pica() {
        izveletaPica = getRandomKey(picas);
        kopejaCena += picas.get(izveletaPica);

        izveletsIzmers = getRandomKey(izmeri);
        kopejaCena += izmeri.get(izveletsIzmers);

        izveletaUzkoda = getRandomKey(uzkodas);
        kopejaCena += uzkodas.get(izveletaUzkoda);

        izveletaMerce = getRandomKey(merces);
        kopejaCena += merces.get(izveletaMerce);

        izveletsDzeriens = getRandomKey(dzerieni);
        kopejaCena += dzerieni.get(izveletsDzeriens);
    }

    private String getRandomKey(Map<String, Double> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        return keys.get(rand.nextInt(keys.size()));
    }
}