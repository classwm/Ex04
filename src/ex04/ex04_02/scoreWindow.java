package ex04.ex04_02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

class scoreWindow extends JFrame implements Runnable {

    int scoreWidth = 300, scoreHeight = 200;
    int scorePositionX = NewProg21.windowWidth + 100, scorePositionY = 200;
    public int countSeconds = 0;
    public int displayMinutes = 0;
    public int displaySeconds = 0;
    public String preMinZero = "", preSecZero = "";
    public String displayTimer = "00:00";

    Timer ballTimer = new Timer(1000, new ActionListener() {
     @Override    

        public void actionPerformed(ActionEvent ae) {

            countSeconds++;

            displayMinutes = countSeconds / 60;
            displaySeconds = countSeconds % 60;

            if (displaySeconds < 10) {
                preSecZero = "0";
            } else {
                preSecZero = "";
            }
            if (displayMinutes < 10) {
                preMinZero = "0";
            } else {
                preMinZero = "";
            }
            displayTimer = (preMinZero + displayMinutes + ":" + preSecZero + displaySeconds);
        }
    }); // ballTimer

    public void paint(Graphics g) {
        
        g.clearRect(0, 0, scoreWidth, scoreHeight);
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif",
                Font.BOLD, 20));
        g.drawString("PLAYER 1:", 15, 60);
        g.drawString("PLAYER 2:", 180, 60);
        g.setFont(new Font("SansSerif",
                Font.BOLD, 100));
        g.drawString(Integer.toString(NewProg21.pOneScore), 50, 150);
        g.drawString(Integer.toString(NewProg21.pTwoScore), 220, 150);
        g.setFont(new Font("SansSerif",
                Font.BOLD, 20));
        g.drawString(displayTimer, 134, 185);
    }

    public void run() { // metoda wątku odświeżającego scoreWindow        
        while (true) {
            repaint();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        } // 
    } // score run

    void initScore() {
        setTitle("Wynik Super Gry :)");
        pack();
        setLocation(scorePositionX, scorePositionY);
        setSize(scoreWidth, scoreHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setBackground(Color.YELLOW);
    }
} //scoreWindow