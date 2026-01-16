package Pica;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); 
        
        ImageIcon backgroundIcon = new ImageIcon("GIF/background.gif");
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(new BorderLayout());
        frame.setContentPane(background);
        
        JLabel label = new JLabel("Welcome!", JLabel.CENTER);
        label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 30));
        label.setForeground(java.awt.Color.WHITE);
        
        frame.getContentPane().add(label);

        frame.setVisible(true);

	}

}
