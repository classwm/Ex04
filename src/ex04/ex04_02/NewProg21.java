package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

/**
 * Klasa obiektu odpowiedzalnego za zmiane współrzednych strzału.
 */
//class NewStrzal extends Thread {
//
//    int ballX, ballY, ballRadius = 10;
//    NewProg21 okno;
//    int ballSpeed = 15;
//
//    NewStrzal(NewProg21 okno) {
//        this.okno = okno;
//    }
//
//    public void run() {
//        while (ballY > 0) {
//            ballY -= ballSpeed;
//            try {
//                Thread.sleep(40);
//            } catch (Exception e) {
//            };
//        }
//        okno.list.remove(this);
//    }
//}
class Shot extends Thread {

    NewProg21 okno;
    int ballSpeed = 0 + okno.ballRadius;

    Shot(NewProg21 okno) {
        this.okno = okno;
    }

    public void run() {
        while (okno.ballY > 0 & okno.ballY < okno.windowHeight) {
            if (okno.ballY != okno.pTwoY) {
                okno.prevBallY = okno.ballY;
            okno.ballY -= ballSpeed;
            try {
                Thread.sleep(60);
            } catch (Exception e) {
            }
            }
            
        }
        okno.ballDirection = 0;
    }
}

class MyListener extends MouseAdapter {

    private NewProg21 okno;

    public MyListener(NewProg21 okno) {
        this.okno = okno;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            okno.shot();
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getX() < okno.windowWidth - okno.pWidth) {
            okno.prevOneX = okno.pOneX;
            okno.pOneX = e.getX();
            okno.isMoveOne = true;
        }
    }
}

public class NewProg21 extends JFrame implements KeyListener, Runnable {

    int windowWidth = 500, windowHeight = 600;
    int pOneX = 200, pOneY = 500, pTwoX = 200, pTwoY = 100, pWidth = 50, pHeight = 10;
    int prevOneX = 0, prevTwoX = 0, prevBallX = 0, prevBallY = 0;
    int pSpeed = 5;
    boolean isMoveOne = false, isMoveTwo = false, servOne = true, servTwo = false;
    static int ballX, ballY, ballRadius = 10, ballDirection = 0;

    // List<NewStrzal> list = new LinkedList<NewStrzal>();
    public static void main(String[] args) {
        NewProg21 okno = new NewProg21("Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true);
        new Thread(okno).start();

        okno.addKeyListener(okno);

        MyListener mListener = new MyListener(okno);
        okno.addMouseListener(mListener);
        okno.addMouseMotionListener(mListener);
    }

    NewProg21(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();
        setBackground(Color.yellow);
        setSize(windowWidth, windowHeight);
        setResizable(false);
    }

    /**
     * Inicjalizacja strzalu - uruchomienie watka zmieniajacego wspolrzedne
     * strzalu.
     */
    void shot() {
        if (ballDirection == 0) {
            if (servOne) {
                ballX = (pOneX + (pWidth / 2)) - (ballRadius / 2);
                ballY = pOneY - ballRadius - 1;
                ballDirection = -1;
                new Shot(this).start();
            } else if (servTwo) {
                ballX = pTwoX + pWidth / 2 - ballRadius / 2;
                ballY = pTwoY + ballRadius;
                ballDirection = 1;
                new Shot(this).start();
            }
        }
    }

    void moveP2(int pDirection) {
        if (pDirection == 1 && pTwoX < windowWidth - pWidth) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;
        } else if (pDirection == -1 && pTwoX > 0) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;
        }
    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran

        if (isMoveOne && !servOne) {
            g.clearRect(prevOneX, pOneY, pWidth, pHeight);
            isMoveOne = false;
        }
        if (isMoveOne && servOne) {
            g.clearRect(prevOneX, pOneY - ballRadius, pWidth, pHeight + ballRadius);
            isMoveOne = false;
        }

        if (isMoveTwo && !servTwo) {
            g.clearRect(prevTwoX, pTwoY, pWidth, pHeight);
            isMoveTwo = false;
        }
        if (isMoveTwo && servTwo) {
            g.clearRect(prevTwoX, pTwoY, pWidth, pHeight + ballRadius);
            isMoveTwo = false;
        }

        g.fillRect(pTwoX, pTwoY, pWidth, pHeight);
        g.fillRect(pOneX, pOneY, pWidth, pHeight);

        if (ballDirection == 0 && servOne) {
            // g.clearRect(ballX - (ballRadius / 2), prevBallY - (ballRadius / 2), ballRadius * 2, ballRadius * 2);
            g.fillOval(pOneX + pWidth / 2 - ballRadius / 2, pOneY - ballRadius - 1, ballRadius, ballRadius);
        }

        if (ballDirection == 0 && servTwo) {
            // g.clearRect(ballX - (ballRadius / 2), prevBallY - (ballRadius / 2), ballRadius * 2, ballRadius * 2);
            g.fillOval(pTwoX + pWidth / 2 - ballRadius / 2, pTwoY + ballRadius, ballRadius, ballRadius);
        }

        if (ballDirection != 0) {
            g.clearRect(ballX, prevBallY, ballRadius, ballRadius);
            g.fillOval(ballX, ballY, ballRadius, ballRadius);
        }


    }

    public void run() { // metoda watka odswierzajacego ekran
        while (true) {
            repaint();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            };
        }
    }
//obsluga zdarzen z klawiatury

    public void keyTyped(KeyEvent e) {
        // te metode zostawie jako pusta
    }

    public void keyReleased(KeyEvent e) {
        // te metode zostawie jako pusta
    }

    public void keyPressed(KeyEvent e) { // reaguje jedynie na przycisniecie klawisza
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveP2(-1);
                break;
            case KeyEvent.VK_RIGHT:
                moveP2(1);
                break;
            case KeyEvent.VK_SPACE:
                if (servTwo) {
                shot();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }
} //NewProg21

