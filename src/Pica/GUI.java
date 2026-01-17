package Pica;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class GUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel mainMenu = createMainMenu(frame);
        
        frame.setContentPane(mainMenu);
        frame.setVisible(true);
    }

    private static JPanel createMainMenu(JFrame frame) {
        URL imgUrl = GUI.class.getResource("/GIF/background.gif");
        JLabel background = new JLabel();
        if (imgUrl != null) {
            background.setIcon(new ImageIcon(imgUrl));
        } else {
            background.setOpaque(true);
            background.setBackground(Color.DARK_GRAY);
        }

        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JLabel label = new JLabel("Bobby's picerija 🍕", JLabel.CENTER);
        label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 45));
        label.setForeground(new Color(95, 158, 160));
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(60, 20, 0, 20);
        background.add(label, gbc);

        Dimension buttonSize = new Dimension(270, 50);

        JButton startButton = new JButton("Sākt darba maiņu");
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setPreferredSize(buttonSize);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(60, 179, 113));
        startButton.setForeground(Color.WHITE);

        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 30, 10);
        background.add(startButton, gbc);

        startButton.addActionListener(e -> {
            frame.setContentPane(createLobby(frame));
            frame.revalidate();
        });

        JButton exitButton = new JButton("Iziet");
        exitButton.setFont(new Font("Arial", Font.BOLD, 22));
        exitButton.setPreferredSize(buttonSize);
        exitButton.setFocusable(false);
        exitButton.setBackground(new Color(220, 20, 60));
        exitButton.setForeground(Color.WHITE);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 10, 60, 10);
        background.add(exitButton, gbc);

        exitButton.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(background);
        return panel;
    }

    private static JPanel createLobby(JFrame frame) {
        URL lobbyImgUrl = GUI.class.getResource("/GIF/pizzeria.gif");
        JLabel lobbyBackground = new JLabel();
        
        if (lobbyImgUrl != null) {
            lobbyBackground.setText("<html><img src='" + lobbyImgUrl + "' width='800' height='600'></html>");
        } else {
            lobbyBackground.setOpaque(true);
            lobbyBackground.setBackground(Color.BLACK);
        }

        lobbyBackground.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
    
        URL pngUrl = GUI.class.getResource("/image/cashier.png"); 
        if (pngUrl != null) {
            ImageIcon icon = new ImageIcon(pngUrl);
            Image scaledImage = icon.getImage().getScaledInstance(300, 400, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0; 
            gbc.weighty = 1.0; 
            gbc.anchor = GridBagConstraints.SOUTHEAST; 
            gbc.insets = new Insets(0, 0, 0, 0);    

            lobbyBackground.add(imageLabel, gbc);
        }      
        
        JLabel dialogLabel = new JLabel("", JLabel.CENTER);
        dialogLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dialogLabel.setForeground(Color.white);
        
        dialogLabel.setOpaque(true);
        dialogLabel.setBackground(new Color(0, 0, 0, 180));
        
        dialogLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(95, 158, 160), 3), 
                BorderFactory.createEmptyBorder(15, 25, 15, 25)));
        
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 50, 50, 50);
        lobbyBackground.add(dialogLabel, gbc);
        
        Darbnieks.animateText(dialogLabel, "Labdien! Laipni lūdzam picērijā!");
        

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(lobbyBackground);
        return panel;
    }
   }
