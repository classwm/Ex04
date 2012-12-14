package ex04.ex04_02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

class ScoreWindow extends JFrame implements Runnable {

    int scoreWidth = 300, scoreHeight = 220;
    int scorePositionX = NewProg21.windowWidth + 100, scorePositionY = 200;
    int pOneScoreX = 50, pTwoScoreX = 220;
    int pauseTextX = 1;
    int threadSleepTime = 500;
    public int countSeconds = 0;
    public int displayMinutes = 0;
    public int displaySeconds = 0;
    public String preMinZero = "", preSecZero = "";
    public String displayTimer = "00:00";
    public static boolean isGamePaused = false;
    boolean goingDown = false;
    
    /**
     * Metoda uzywająca Timera do zliczania sekund i wyświetlanai ich w formacie
     * mm:ss
     */
    Timer ballTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {

            if (!isGamePaused) {
                countSeconds++;
                if (countSeconds == 6000) {
                    countSeconds = 1;
                }
            }

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

    /**
     * Metoda rysująca okienko z wynikiem
     *
     * @param g
     */
    public void paint(Graphics g) {

        if (!isGamePaused) {
            g.clearRect(0, 0, scoreWidth, scoreHeight);

            g.setColor(Color.BLACK);

            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            g.drawString(NewProg21.pOneName, 20, 60);
            g.drawString(NewProg21.pTwoName, 185, 60);

            g.setFont(new Font("SansSerif", Font.BOLD, 100));
            if (NewProg21.pOneScore > 9) {
                pOneScoreX = 10;
            }
            if (NewProg21.pTwoScore > 9) {
                pTwoScoreX = 180;
            }
            g.drawString(Integer.toString(NewProg21.pOneScore), pOneScoreX, 150);
            g.drawString(Integer.toString(NewProg21.pTwoScore), pTwoScoreX, 150);

            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            g.drawString(displayTimer, 134, 185);

            g.setColor(Color.GRAY);
            g.setFont(new Font("SansSerif", Font.BOLD, 15));
            g.drawString("Naciśnij ESC dla pauzy i zmiany gracza 2", 8, 212);
        }
        if (isGamePaused) {
            g.clearRect(0, 198, scoreWidth, 19);
            g.setFont(new Font("SansSerif", Font.BOLD, 18));
            g.drawString("GRA ZATRZYMANA", pauseTextX, 212);
            if (pauseTextX < 130 && !goingDown) {
                pauseTextX++;
            } else if (pauseTextX > 1 && goingDown) {
                pauseTextX--;
            }
            if (pauseTextX == 130) {
                goingDown = true;
            }
            if (pauseTextX == 1) {
                goingDown = false;
            }
        }
    }

    /**
     * Watek ovbsługujący okienko z wynikiem
     */
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                if (!isGamePaused) {
                    threadSleepTime = 500;
                } else {
                    threadSleepTime = 10;
                }
                Thread.sleep(threadSleepTime);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    } // score run

    void initScore() {
        setTitle("SwingPong Wyniki");
        pack();
        setLocation(scorePositionX, scorePositionY);
        setSize(scoreWidth, scoreHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setBackground(Color.YELLOW);
    }
} //scoreWindow