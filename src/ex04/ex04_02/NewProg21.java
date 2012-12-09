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
//    int x_strzal, y_strzal, h_strzal = 10;
//    NewProg21 okno;
//    int predkosc = 15;
//
//    NewStrzal(NewProg21 okno) {
//        this.okno = okno;
//    }
//
//    public void run() {
//        while (y_strzal > 0) {
//            y_strzal -= predkosc;
//            try {
//                Thread.sleep(40);
//            } catch (Exception e) {
//            };
//        }
//        okno.list.remove(this);
//    }
//}

class Strzal extends Thread {

    NewProg21 okno;
    int predkosc = 45;

    Strzal(NewProg21 okno) {
        this.okno = okno;
    }

    public void run() {
        while (okno.y_strzal > 0) {
            okno.y_strzal -= predkosc;
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            };
        }
        okno.jestStrzal = false;
    }
}


class MyListener extends MouseAdapter {

    private NewProg21 okno;

    public MyListener(NewProg21 okno) {
        this.okno = okno;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            okno.strzal();
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
    int prevOneX, prevTwoX = 0;
    boolean isMoveOne, isMoveTwo = true;
    int pSpeed = 5;
    static int x_strzal, y_strzal, h_strzal = 10;
    static boolean jestStrzal = false;
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
     * Inicjalizacja strzalu - uruchomienie watka zmieniajacego wspolrzedne strzalu.
     */
    void strzal() {
        if (!jestStrzal) {
            x_strzal = pOneX + pWidth / 2 - h_strzal / 2;
            y_strzal = pOneY - h_strzal;
            jestStrzal = true;
            new Strzal(this).start();
        }
    }


    void moveP2(int pDirection) {
        if (pDirection == 1 && pTwoX < windowWidth - pWidth) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;            
        } else
            if (pDirection == -1 && pTwoX > 0) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;            
        }
    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran
        if (isMoveTwo) {
            g.clearRect(prevTwoX, pTwoY, pWidth, pHeight);
            isMoveTwo = false;
        }
        if (isMoveOne) {
            g.clearRect(prevOneX, pOneY, pWidth, pHeight);
            isMoveOne = false;
        }
        g.fillRect(pTwoX, pTwoY, pWidth, pHeight);
        g.fillRect(pOneX, pOneY, pWidth, pHeight);


        if (jestStrzal) {
            g.fillOval(x_strzal, y_strzal, h_strzal, h_strzal);
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
                strzal();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }
} //NewProg21

