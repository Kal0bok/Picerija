package Pica;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GalvenaKlase {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome!", JLabel.CENTER);
        frame.getContentPane().add(label);

        frame.setVisible(true);

	}

}
