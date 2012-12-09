package ex04.co04_02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Prog19 extends JFrame implements ActionListener {

    JButton b1, b2, b3;
    JTextField text;

    public static void main(String[] args) {
        Prog19 okno = new Prog19("Okno z przyciskiem");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.pack();
        okno.setVisible(true);
    }

    Prog19(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();
        setResizable(false);
        cp.setLayout(new BorderLayout());

        b1 = new JButton("Lewy");
        cp.add(b1, BorderLayout.WEST);
        b1.addActionListener(this); // b1 ma reagowac na przycisniecie, mowie ze klasa nasłuchujaca jest wlasnie aktualna klasa

        text = new JTextField(20);
        text.setEnabled(false);
        text.setDisabledTextColor(Color.red);
        cp.add(text);
        text.setText("kilknij cos");
        
        b2 = new JButton("Prawy");
        cp.add(b2, BorderLayout.EAST);
        b2.addActionListener(this);

        b3 = new JButton("Dolny");
        cp.add(b3, BorderLayout.SOUTH);
        b3.addActionListener(this);

    }

    public void actionPerformed(ActionEvent zdarzenie) { // procedure, nasłuchujaca sygnały
        Object zrodlo = zdarzenie.getSource();
        if (zrodlo == b1) { // sprawdzanie, z którym przyciskiem zwiazane jest to zdarzenie
            text.setText("Lewy. ActionCommand = "
                    + zdarzenie.getActionCommand());
        } else if (zrodlo == b2) {
            text.setText("Prawy. ActionCommand = "
                    + zdarzenie.getActionCommand());
        } else if (zrodlo == b3) {
            text.setText("Dolny. ActionCommand = "
                    + zdarzenie.getActionCommand());
        }
    }
}