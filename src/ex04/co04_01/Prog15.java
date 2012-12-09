package ex04.co04_01;

import java.awt.*;
import javax.swing.*;

public class Prog15 extends JFrame {

    public static void main(String[] args) {
        Prog15 okno = new Prog15("Okno z BorderLayout");
        okno.init();
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.show();
    }

    Prog15(String tytul) {

        super(tytul);
    }

    void init() {
        setSize(500, 500);
        Container cp = getContentPane();

        cp.setLayout(new BorderLayout()); // wybieram układacz brzegowy

        JTextField t1 = new JTextField("południe");
        JButton cb1 = new JButton("wschód");
        JButton cb2 = new JButton("północ");
        JButton cb3 = new JButton("zachód");
        JButton cb4 = new JButton("srodek");

        // i dodaje przyciski do naszego okna na wszystkich stronach swiata
        cp.add(t1, BorderLayout.SOUTH);
        cp.add(cb1, BorderLayout.EAST);
        cp.add(cb2, BorderLayout.NORTH);
        cp.add(cb3, BorderLayout.WEST);
        cp.add(cb4, BorderLayout.CENTER);
    }
}