package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

/**
 * Klasa obiektu odpowiedzalnego za zmiane współrzednych strzału.
 */
class NewStrzal extends Thread {

    int x_strzal, y_strzal, h_strzal = 10;
    NewProg21 okno;
    int predkosc = 15;

    NewStrzal(NewProg21 okno) {
        this.okno = okno;
    }

    public void run() {
        while (y_strzal > 0) {
            y_strzal -= predkosc;
            try {
                Thread.sleep(40);
            } catch (Exception e) {
            };
        }
        okno.list.remove(this);
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
        if (e.getX() < okno.w - okno.szerokosc) {
            okno.prevX = okno.x;
            okno.x = e.getX();
            okno.isMove = true;
        }
    }
}

public class NewProg21 extends JFrame implements KeyListener, Runnable {

    int w = 500, h = 500;
    int x = 200, y = 400, x2 = 200, y2 = 100, szerokosc = 50, wysokosc = 10;
    int prevX, prevX2 = 0;
    boolean isMove, isMove2 = true;
    int predkosc = 5;
    List<NewStrzal> list = new LinkedList<NewStrzal>();

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
        setSize(w, h);
        setResizable(false);
    }

    /**
     * Inicjalizacja strzalu - uruchomienie watka zmieniajacego wspolrzedne
     * strzalu.
     */
    void strzal() {

        NewStrzal strzal = new NewStrzal(this);
        strzal.h_strzal = 10;
        strzal.x_strzal = x + szerokosc / 2 - strzal.h_strzal / 2;
        strzal.y_strzal = y - strzal.h_strzal;

        list.add(strzal);
        strzal.start();
    }

//    void przesun(int kierunek) {
//        x += (kierunek * predkosc);
//    }
    void przesun2(int kierunek) {
        if (kierunek == 1 && x2 < w - szerokosc) {
            prevX2 = x2;
            x2 += (kierunek * predkosc);
            isMove2 = true;            
            System.out.println("prawo: " + x2);
            
        } else
            if (kierunek == -1 && x2 > 0) {
            prevX2 = x2;
            x2 += (kierunek * predkosc);
            isMove2 = true;
            System.out.println("lewo: " + x2);
        }
    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran
        if (isMove2) {
            g.clearRect(prevX2, y2, szerokosc, wysokosc);
            isMove2 = false;
        }
        if (isMove) {
            g.clearRect(prevX, y, szerokosc, wysokosc);
            isMove2 = false;
        }
        g.fillRect(x2, y2, szerokosc, wysokosc);
        g.fillRect(x, y, szerokosc, wysokosc);


        for (NewStrzal strzal : list) {
            g.fillOval(strzal.x_strzal, strzal.y_strzal, strzal.h_strzal, strzal.h_strzal);
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
                przesun2(-1);
                break;
            case KeyEvent.VK_RIGHT:
                przesun2(1);
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

