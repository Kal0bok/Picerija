package Pica;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GalvenaKlase {
    public static String[] vards = {"L", "a", "b", "d", "i", "e", "n", "!"};
    private static int index = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        
        JLabel label = new JLabel("", JLabel.CENTER);
        frame.getContentPane().add(label);
        frame.setVisible(true);

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (index < vards.length) {
                	label.setText(label.getText() + vards[index]);
                    index++;
                } else {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 500);
    }
}