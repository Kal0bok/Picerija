package Pica;

import java.awt.Color;
import java.awt.Dimension;
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
        JLabel background = new JLabel();
        
        if (imgUrl != null) {
            background.setIcon(new ImageIcon(imgUrl));
        } else {
            background.setOpaque(true);
            background.setBackground(Color.DARK_GRAY);
        }

        background.setLayout(new GridBagLayout());
        frame.setContentPane(background);

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
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(10, 10, 30, 10); 
        background.add(startButton, gbc);

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

        frame.setVisible(true);
    }
}