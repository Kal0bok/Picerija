package Pica;

import java.util.Random;

public class Pica {
    private Random rand = new Random();

    public static final String[] PICAS = {"Margarita", "Peperoni", "Havajas", "Studentu"};
    public static final double[] PICAS_CENAS = {8.0, 10.0, 11.0, 7.5};

    public static final String[] IZMIRI = {"25cm", "30cm", "50cm"};
    public static final double[] IZMIRI_CENAS = {0.0, 2.0, 5.0};

    public static final String[] MERCE = {"Kiploku", "Asa", "Kecups"};
    public static final double MERCE_CENA = 0.50;

    public static final String[] UZKODAS = {"Fri", "Sipolu gredzeni", "Nekas"};
    public static final double[] UZKODAS_CENAS = {2.50, 3.00, 0.0};

    public static final String[] DZERIENI = {"Kola", "Udens", "Nekas"};
    public static final double[] DZERIENI_CENAS = {1.50, 1.00, 0.0};

    public String izveletaPica, izveletsIzmers, izveletaMerce, izveletaUzkoda, izveletsDzeriens;
    public double kopejaCena = 0;

    public Pica() {
        int pIdx = rand.nextInt(PICAS.length);
        izveletaPica = PICAS[pIdx];
        kopejaCena += PICAS_CENAS[pIdx];

        int iIdx = rand.nextInt(IZMIRI.length);
        izveletsIzmers = IZMIRI[iIdx];
        kopejaCena += IZMIRI_CENAS[iIdx];

        int mIdx = rand.nextInt(MERCE.length);
        izveletaMerce = MERCE[mIdx];
        kopejaCena += MERCE_CENA;

        int uIdx = rand.nextInt(UZKODAS.length);
        izveletaUzkoda = UZKODAS[uIdx];
        kopejaCena += UZKODAS_CENAS[uIdx];

        int dIdx = rand.nextInt(DZERIENI.length);
        izveletsDzeriens = DZERIENI[dIdx];
        kopejaCena += DZERIENI_CENAS[dIdx];
    }
}