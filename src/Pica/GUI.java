package Pica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); 
        
        URL imgUrl = GUI.class.getResource("/GIF/background.gif");
        
        if (imgUrl != null) {
            ImageIcon backgroundIcon = new ImageIcon(imgUrl);
            JLabel background = new JLabel(backgroundIcon);
            background.setLayout(new BorderLayout());
            frame.setContentPane(background);
        } else {
            System.err.println("Kļuda: Fails nav atrsts : src/GIF/background.gif");
            frame.getContentPane().setBackground(Color.DARK_GRAY);
        }
        
        background.setLayout(new GridBagLayout());
        frame.setContentPane(background);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.anchor = GridBagConstraints.CENTER; 

        JLabel label = new JLabel("Welcome!");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setForeground(Color.WHITE);
        gbc.gridy = 0; 
        background.add(label, gbc);

        JButton startButton = new JButton("Sākt darba maiņu");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 1; 
        background.add(startButton, gbc);

        JButton exitButton = new JButton("Iziet");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridy = 2; 
        background.add(exitButton, gbc);
        
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}