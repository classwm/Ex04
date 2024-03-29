package ex04.co04_02;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

/**
 * Klasa obiektu odpowiedzalnego za zmiane współrzednych strzału.
 */
class Strzal extends Thread {

    // int x_strzal, y_strzal, h_strzal = 10;
    Prog21 okno;
    int predkosc = 10;

    Strzal(Prog21 okno) {
        this.okno = okno;
    }

    public void run() {
        while (okno.y_strzal > 0) {
            okno.y_strzal -= predkosc;
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        okno.jestStrzal = false;
    }
}

class MyListener extends MouseAdapter {

    private Prog21 okno;

    public MyListener(Prog21 okno) {
        this.okno = okno;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            okno.strzal();
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getX() < (okno.w - okno.szerokosc)) {
            okno.x = e.getX();
        }
    }
}

public class Prog21 extends JFrame implements KeyListener, Runnable {

    int w = 500, h = 500;
    //paletka1
    int x = 200, y = 400, szerokosc = 50, wysokosc = 10;
    int predkosc = 5;
    //paletka2
    int x2 = 400, y2 = 100;
    static int x_strzal, y_strzal, h_strzal = 10;
    static boolean jestStrzal = false;

    public static void main(String[] args) {
        Prog21 okno = new Prog21("Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true);
        new Thread(okno).start();

        okno.addKeyListener(okno);

        MyListener mListener = new MyListener(okno);
        okno.addMouseListener(mListener);
        okno.addMouseMotionListener(mListener);
    }

    Prog21(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();
        setBackground(Color.yellow);
        setSize(w, h);
        setResizable(false);
    }

    /**
     * Inicjalizacja strzalu - uruchomienie watka zmieniajacego wspolrzedne strzalu.
     */
    void strzal() {
        if (!jestStrzal) {
            x_strzal = x + szerokosc / 2 - h_strzal / 2;
            y_strzal = y - h_strzal;
            jestStrzal = true;
            new Strzal(this).start();
        }

    }
//
//    void przesun(int kierunek) {
//        x += (kierunek * predkosc);
//    }

    void przesun(int kierunek) {
        if (kierunek == 1 && x2 < w - szerokosc) {
            x2 += (kierunek * predkosc);
        } else if (kierunek == -1 && x2 > 0) {
            x2 += (kierunek * predkosc);
        }

    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran
        g.clearRect(0, 0, w, h);
        g.fillRect(x, y, szerokosc, wysokosc);
        g.fillRect(x2, y2, szerokosc, wysokosc);
        if (jestStrzal) {
            g.fillOval(x_strzal, y_strzal, h_strzal, h_strzal);
        }


    }

    public void run() { // metoda watka odswiezajacego ekran
        while (true) {
            repaint();
            try {
                Thread.sleep(50);
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

    public void keyPressed(KeyEvent e) { // reaguje na samo przycisniecie klawisza
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                przesun(-1);
                break;
            case KeyEvent.VK_RIGHT:
                przesun(1);
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
