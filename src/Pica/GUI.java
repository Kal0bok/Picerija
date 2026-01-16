package Pica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;
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
        
        JLabel label = new JLabel("Welcome!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setForeground(Color.WHITE);
        
        frame.add(label);

        frame.setVisible(true);
    }
}