package Pica;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GalvenaKlase {
	public String[] vards = {"Labdien"};
	
	public String chat() {
	for(int i=0; i<vards.length;i++) {
		timer.scheduleAtFixedRate(vards, 0, 0.5);
	}
	return null;
	}
	
	public static void main(String[] args) {
		
		Timer timer = new Timer(); 

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Izpildits"); 
            }
        };
		
		JFrame frame = new JFrame("Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome!", JLabel.CENTER);
        frame.getContentPane().add(label);

        frame.setVisible(true);

	}

}
