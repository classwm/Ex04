package ex04.co04_02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Prog22 extends JFrame
        implements MouseListener, MouseMotionListener {

    JLabel pole1 = new JLabel("");
    JLabel pole2 = new JLabel("");

    public static void main(String[] args) {
        Prog22 okno = new Prog22("Myszka");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true);

        okno.addMouseListener(okno); // klasa ma nasłuchiwac zdarzenia, pochodzace od myszki
        okno.addMouseMotionListener(okno);
    }

    Prog22(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();
        setSize(500, 300);
        setResizable(false);
        cp.setBackground(Color.green);
        cp.setLayout(new GridLayout(2, 1));
        cp.add(pole1);
        cp.add(pole2);
    }

    // Obsluga zdarzen od myszki:
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        pole1.setText(
                "Wcisnieto myszke: X = "
                + x
                + ", Y = "
                + y
                + " Wcisniety przycisk: "
                + e.getButton());
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        pole2.setText("Ruch myszki: X = " + x + ", Y = " + y);
    }
}