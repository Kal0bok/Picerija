package Pica;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class GUI {
    // Очередь для хранения заказов
    private static Queue<String> pasutijumuRinda = new LinkedList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bobby's Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.setContentPane(createMainMenu(frame));
        frame.setVisible(true);
    }

    private static JPanel createMainMenu(JFrame frame) {
        // Твой код фона
        URL imgUrl = GUI.class.getResource("/GIF/background.gif");
        JLabel background = new JLabel();
        if (imgUrl != null) background.setIcon(new ImageIcon(imgUrl));
        else background.setBackground(Color.DARK_GRAY);

        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel("Bobby's picerija 🍕", JLabel.CENTER);
        label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 45));
        label.setForeground(new Color(95, 158, 160));
        gbc.gridy = 0; gbc.insets = new Insets(60, 20, 30, 20);
        background.add(label, gbc);

        JButton startButton = new JButton("Sākt darba maiņu");
        startButton.setPreferredSize(new Dimension(270, 50));
        startButton.addActionListener(e -> {
            frame.setContentPane(createLobby(frame));
            frame.revalidate();
        });

        gbc.gridy = 1;
        background.add(startButton, gbc);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(background);
        return panel;
    }

    private static JPanel createLobby(JFrame frame) {
        JPanel lobby = new JPanel(new BorderLayout());
        // Здесь твоя логика с кассиром и R-keeper
        
        JButton rKeeper = new JButton("R-keeper ☰");
        rKeeper.addActionListener(e -> {
            showRKeeperMenu(frame);
        });

        lobby.add(rKeeper, BorderLayout.NORTH);
        // Добавь сюда своего анимированного кассира из Darbnieks.animateText
        return lobby;
    }

    private static void showRKeeperMenu(JFrame frame) {
        String[] options = {"Jauns pasūtījums", "Skatīt rindu", "Apkalpot klientu", "Iziet"};
        int choice = JOptionPane.showOptionDialog(frame, "Izvēlieties darbību:", "R-keeper",
                0, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0: // Новый заказ
                izveidotPasutijumu(frame);
                break;
            case 1: // Посмотреть очередь
                if (pasutijumuRinda.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Rinda ir tukša!");
                } else {
                    JOptionPane.showMessageDialog(frame, String.join("\n", pasutijumuRinda));
                }
                break;
            case 2: // Обслужить
                if (!pasutijumuRinda.isEmpty()) {
                    String pabeigts = pasutijumuRinda.poll();
                    JOptionPane.showMessageDialog(frame, "Gatavs: " + pabeigts);
                }
                break;
        }
    }

    private static void izveidotPasutijumu(JFrame frame) {
        String vards = Metodes.virknesParbaude("Klienta vārds:", "Jānis");
        if (vards == null) return;

        String tel = Metodes.telefonaParbaude();
        if (tel == null) return;

        String[] picas = {"Margarita (8€)", "Peperoni (10€)", "Havajas (11€)"};
        String izvele = (String) JOptionPane.showInputDialog(frame, "Izvēlies picu:", "Ēdienkarte",
                JOptionPane.QUESTION_MESSAGE, null, picas, picas[0]);

        if (izvele != null) {
            String pasutijums = vards + " (" + tel + ") - " + izvele;
            pasutijumuRinda.add(pasutijums);
            JOptionPane.showMessageDialog(frame, "Pasūtījums pievienots rindā!");
        }
    }
}