package Pica;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Darbnieks {

    public static void main(String[] args) {
        
    }

    public static void animateText(JLabel label, String teksts) {
        char[] characters = teksts.toCharArray();
        final int[] index = {0};
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (index[0] < characters.length) {
                    SwingUtilities.invokeLater(() -> {
                        label.setText(label.getText() + characters[index[0]]);
                        index[0]++;
                    });
                } else {
                    timer.cancel();
                }
                
                try {
                    Thread.sleep(2000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 100);
    }
}