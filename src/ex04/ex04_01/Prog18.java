package ex04.ex04_01;

import java.awt.*;
import javax.swing.*;

public class Prog18 extends JFrame {

    public static void main(String[] args) {
        Prog18 okno = new Prog18("Okno z czym sie da");
        okno.init();
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);

        okno.setResizable(false);
        okno.setVisible(true);
        okno.pack();
    }

    Prog18(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();

        cp.setLayout(new BorderLayout()); // wybieram układacz brzegowy dla całego okna

        JPanel p1 = new JPanel(); // tworze Panel by na nim umiescic przyciski
        p1.setLayout(new FlowLayout()); // dla p1 ustawiam rozkład FlowLayout
        JButton b1 = new JButton("Graj");
        JButton b2 = new JButton("Zatrzymaj");
        p1.add(b1);
        p1.add(b2); // dodałem przyciski
        cp.add(p1, BorderLayout.SOUTH); // dodaje panel na północy głównego okna

        JPanel p2 = new JPanel(); // teraz tworze nowy panel z rozkładem GridLayout
        p2.setLayout(new GridLayout(3, 1));
        JCheckBox cb1 = new JCheckBox("1");
        JCheckBox cb2 = new JCheckBox("2");
        JCheckBox cb3 = new JCheckBox("3");
        p2.add(cb1);
        p2.add(cb2);
        p2.add(cb3);
        cp.add(p2, BorderLayout.EAST); //dodaje panel na północy głównego okna

        JPanel p3 = new JPanel(); //teraz tworze nowy panel z rozkładem FlowLayout
        p3.setLayout(new FlowLayout());
        JTextField text = new JTextField(20);
        p3.add(text);
        text.setText("Oto text");
        JButton b3 = new JButton("Pierwszy");
        p3.add(b3);
        cp.add(p3, BorderLayout.WEST);
    }
}