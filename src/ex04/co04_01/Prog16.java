package ex04.co04_01;

import java.awt.*;
import javax.swing.*;

public class Prog16 extends JFrame {

    public static void main(String[] args) {
        Prog16 okno = new Prog16("Okno z GridLayout");
        okno.init();
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.setVisible(true);
    }

    Prog16(String tytul) {
        super(tytul);
    }

    void init() {
        setSize(500, 500);
        Container cp = getContentPane();

        cp.setLayout(new GridLayout(5, 4)); //wybieram układacz siatkowy
        for (int i = 0; i < 19; i++) {
            cp.add(new JButton("Przycisk nr " + i));
        }
        cp.add(new JPanel());
    }
}