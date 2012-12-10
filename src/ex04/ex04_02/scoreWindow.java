
package ex04.ex04_02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;


 class scoreWindow extends JFrame implements Runnable {
        int scoreWidth = 300, scoreHeight = 180;
        int scorePositionX = NewProg21.windowWidth + 100, scorePositionY = 200; 
        public void paint(Graphics g) {
            g.clearRect(0, 0, 300, 180);
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif",
                    Font.BOLD, 20));
            g.drawString("PLAYER 1:", 15, 60);
            g.drawString("PLAYER 2:", 180, 60);
            g.setFont(new Font("SansSerif",
                    Font.BOLD, 100));
            g.drawString(Integer.toString(NewProg21.pOneScore), 50, 150);
            g.drawString(Integer.toString(NewProg21.pTwoScore), 220, 150);

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