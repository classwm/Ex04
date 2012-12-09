package ex04.co04_03;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Prog34 extends JFrame
        implements ActionListener {

    JButton[] przyciski = {
        new JButton("Okno do wpisywania"),
        new JButton("Ostrzezenie"),
        new JButton("Dialog info"),
        new JButton("Dialog potwierdzenia"),
        new JButton("Wybierz plik")
    };

    public static void main(String[] args) {
        Prog34 okno = new Prog34("Przyklady dialogów");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.show();
    }

    Prog34(String tytul) {
        super(tytul);
    }

    void init() {
        setSize(500, 300);
        setResizable(false);

        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        for (int i = 0; i < przyciski.length; i++) {
            przyciski[i].addActionListener(this);
            cp.add(przyciski[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        JFileChooser plik = new JFileChooser();
        int wybor = -1;
        for (int i = 0; i < przyciski.length; i++) {
            if (obj == przyciski[i]) {
                wybor = i;
                break;
            }
        }
        switch (wybor) {
            case 0:
                String wpis = JOptionPane.showInputDialog("Wpisz cos");
                setTitle(wpis);
                break;
            case 1:
                JOptionPane.showMessageDialog(null,
                        "Uwaga pilot wyskoczył",
                        "Ostrzezenie",
                        JOptionPane.WARNING_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null,
                        "Temperatura procesora 95 stopni",
                        "Informacja",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                int co = JOptionPane.showConfirmDialog(null,
                        "Czy sformatowac dysk c:");
                if (co == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null,
                            "Wedle rozkazu",
                            "Informacja",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case 4:
                plik.showOpenDialog(this);
                
                java.io.File f = plik.getSelectedFile();
                if (f != null) {
                    String nazwa = f.getAbsolutePath();
                    JOptionPane.showMessageDialog(null, nazwa,
                            "Wybrałes plik",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "nie wybrałes zadnego pliku",
                            "Wybrany plik",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                break;
        }
    }
}