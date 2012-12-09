package ex04.co04_01;

import java.awt.*;
import javax.swing.*;

public class Prog13 extends JFrame implements Runnable {

    int x, y;
    int k = 1;

    public static void main(String[] args) {
        Prog13 okno = new Prog13("Mój ruchomy napis");
        okno.init(); //inicjujemy
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.setUndecorated(true); // okno bez ramki
        okno.setLocation(300, 200); // wspołezedne okna na ekranie
        okno.setVisible(true); //i pokazujemy okno.
        new Thread(okno).start(); // animacja w oddzielnym watku, nowy wątek z oknem (3 wątki - main, nowe okno, oraz wątek repaint z wnętrzem okna) 
    }

    Prog13(String tytul) {
        super(tytul);
    }

    void init() {
        x = 25;
        y = 25;
        setSize(400, 400);
        setBackground(Color.yellow);
        setResizable(false);
    }

    public void run() { // metoda kyóra ciagle bedzie wywoływala metode repaint
        while (true) {
            repaint();
            try {
                Thread.sleep(7);
            } catch (Exception e) {
            }
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setColor(Color.green);
        Font fo = new Font("Roman", Font.PLAIN | Font.BOLD, 24);
        g.setFont(fo);
        g.drawString("Ala ma kota", x, y);
        if (x >= 270) {
            dispose();
            System.exit(0);
        }
        k *= ((x == 300) || (x == 24)) ? -1 : 1;
        x += k;
        y += k;
        
    }
}