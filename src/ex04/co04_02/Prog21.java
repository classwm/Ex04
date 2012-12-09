package ex04.co04_02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Klasa obiektu odpowiedzalnego za zmiane współrzednych strzału.
 */
class Strzal extends Thread {

    Prog21 okno;
    int predkosc = 45;

    Strzal(Prog21 okno) {
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

public class Prog21 extends JFrame implements KeyListener, Runnable {

    int w = 500, h = 500;
    int x = 200, y = 400, szerokosc = 60, wysokosc = 15;
    int predkosc = 20;
    static int x_strzal, y_strzal, h_strzal = 10;
    static boolean jestStrzal = false;

    public static void main(String[] args) {
        Prog21 okno = new Prog21("Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true);
        new Thread(okno).start();

        okno.addKeyListener(okno);
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

    void przesun(int kierunek) {
        x += (kierunek * predkosc);
    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran
        g.clearRect(0, 0, w, h);
        g.fillRect(x, y, szerokosc, wysokosc);
        if (jestStrzal) {
            g.fillOval(x_strzal, y_strzal, h_strzal, h_strzal);
        }
    }

    public void run() { // metoda watka odswierzajacego ekran
        while (true) {
            repaint();
            try {
                Thread.sleep(20);
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
}