package ex04.co04_03;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Prog23 extends JFrame implements WindowListener {

    JLabel napis = new JLabel("Witam");

    public static void main(String[] args) {
        Prog23 okno = new Prog23("Swiadome okno");
        okno.init();
        okno.show();
        okno.addWindowListener(okno);
    }

    Prog23(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();
        setSize(500, 300);
        setResizable(false);
        cp.setBackground(Color.pink);
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.NORTH, napis);
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
        napis.setText("Znów wróciłes :)");
    }

    public void windowDeactivated(WindowEvent e) {
        napis.setText("Opuszczasz mnie :( ????");
    }
}