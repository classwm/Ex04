package ex04.co04_02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prog20 extends JFrame implements KeyListener {

    JLabel pole1 = new JLabel("Wcisnij klawisz");
    JLabel pole2 = new JLabel("Wcisnij klawisz");
    JLabel pole3 = new JLabel("Wcisnij klawisz");
    JLabel pole4 = new JLabel("Wcisnij klawisz");
    JLabel pole5 = new JLabel("Wcisnij klawisz");
    JLabel pole6 = new JLabel("Wcisnij klawisz");

    public static void main(String[] args) {
        Prog20 okno = new Prog20("Przyciskacz");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true);

        okno.addKeyListener(okno); // mowimy ze ta klasa ma nasłuchiwac zdarzenia pochodzace od klawiatury,
        // a opis poszczególnych metod jest zdefiniowana w obiekcie okno
    }

    Prog20(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();

        setSize(300, 200);
        setResizable(false);
        cp.setBackground(Color.cyan);
        cp.setLayout(new GridLayout(6, 1));
        cp.add(pole1);
        cp.add(pole2);
        cp.add(pole3);
        cp.add(pole4);
        cp.add(pole5);
        cp.add(pole6);
    }

    // Obsluga zdarzen z klawiatury:
    public void keyTyped(KeyEvent e) {
        // te metode zostawie jako pusta
//        pole1.setText("Wcisnieto klawisz o kodzie: " + e.getKeyChar());
//        pole2.setText("Wcisnieto klawisz: " + e.getKeyCode());
//        pole3.setText("Klawisz funkcyjny: " + e.isActionKey());
//        pole4.setText("Nr klawisza o tym kodzie: " + e.getKeyLocation());
//        pole5.setText("Nazwa klawisza: " + e.getKeyText(e.getKeyCode()));
//        pole6.setText("Modyfikator: " + e.getKeyModifiersText(e.getModifiers()));
    }

    public void keyPressed(KeyEvent e) { // pobieram rózne (nie wszystkie) informacje o przycisnietym klawiszu
        pole1.setText("Wcisnieto klawisz o kodzie: " + e.getKeyChar());
        pole2.setText("Wcisnieto klawisz: " + e.getKeyCode());
        pole3.setText("Klawisz funkcyjny: " + e.isActionKey());
        pole4.setText("Nr klawisza o tym kodzie: " + e.getKeyLocation());
        pole5.setText("Nazwa klawisza: " + e.getKeyText(e.getKeyCode()));
        pole6.setText("Modyfikator: " + e.getKeyModifiersText(e.getModifiers()));
    }

    public void keyReleased(KeyEvent e) {
        pole1.setText("Puszczono klawisz o kodzie: " + e.getKeyChar());
        pole2.setText("Puszczono klawisz: " + e.getKeyCode());
        pole3.setText("Klawisz funkcyjny: " + e.isActionKey());
        pole4.setText("Nr klawisza o tym kodzie: " + e.getKeyLocation());
        pole5.setText("Nazwa klawisza: " + e.getKeyText(e.getKeyCode()));
        pole6.setText("Modyfikator: " + e.getKeyModifiersText(e.getModifiers()));
    }
}