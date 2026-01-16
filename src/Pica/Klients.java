package Pica;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Klients {

    public static void main(String[] args) {
        sakumsSveiciens("Labdien! Laipni lūdzam picērijā!");
    }

    public static void sakumsSveiciens(String teksts) {
        JFrame frame = new JFrame("Picerija");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("", JLabel.CENTER);
        frame.getContentPane().add(label);
        frame.setVisible(true);

        char[] chat = teksts.toCharArray();
        final int[] index = {0}; 
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (index[0] < chat.length) {
                    label.setText(label.getText() + chat[index[0]]);
                    index[0]++;
                } else {
                    timer.cancel();
                }
            }
        };
        
        timer.scheduleAtFixedRate(task, 500, 200);
    }
}