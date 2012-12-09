package ex04.co04_03;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Oddzielna klasa do nasłuchiwania pisze tylko te metody które mnie interesuja
 */
class Nasluchiwacz extends WindowAdapter {
    Prog24 okno; // musze miec dostep do okna by moc tam cos zmieniac

    Nasluchiwacz(Prog24 okno) {
        this.okno = okno;
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowActivated(WindowEvent e) {
        okno.napis.setText("Znów wróciłes :)");
    }

    public void windowDeactivated(WindowEvent e) {
        okno.napis.setText("Opuszczasz mnie :( ????");
    }
}

public class Prog24 extends JFrame {

    JLabel napis = new JLabel("Witam");

    public static void main(String[] args) {
        Prog24 okno = new Prog24("Swiadome okno z adapterem");
        okno.init();

        Nasluchiwacz gumoweUcho = new Nasluchiwacz(okno); // tworze obiekt nasłuchujacy
        okno.addWindowListener(gumoweUcho); // rejestruje obsługe

        okno.setVisible(true);
    }

    Prog24(String tytul) {
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
}
