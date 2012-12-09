package ex04.co04_03;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Prog26
        extends JFrame
        implements ActionListener, ListSelectionListener, ItemListener {

    JTextField tekst = new JTextField(30);
    Vector dane = new Vector(); // kolekcja dzialajaca jak ArrayList, tylko ze zsynchronizowana
    JList lista = new JList(dane); // obiekt listy bierze dane z Vectora
    JScrollPane sp = new JScrollPane(lista);
    String tablica[] = {"jeden", "dwa", "trzy"};
    JComboBox cBox = new JComboBox(tablica); // tablica rozwijalna
    JButton plist = new JButton("Dodaj do listy");
    JButton pcbox = new JButton("Dodaj do ComboBox");
    JButton minlist = new JButton("Usuń z listy");

    public static void main(String[] args) {
        Prog26 okno = new Prog26("Troche komponentów");
        okno.init();
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.setResizable(false);
        okno.setVisible(true);
    }

    Prog26(String tytul) {
        super(tytul);
    }

    void init() {
        setSize(1000, 200);
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());

        dane.add("Kraków"); // dodaje do listy
        dane.add("Nowy Sacz");
        dane.add("Tarnów");
        dane.add("Warszawa");

        plist.addActionListener(this); // dodajemy nasłuchiwacz do przyciskow
        pcbox.addActionListener(this);
        minlist.addActionListener(this);

        lista.addListSelectionListener(this); // dodaje nasłuchiwacze wyboru do list
        cBox.addItemListener(this);

        cp.add(sp);
        cp.add(tekst);
        cp.add(cBox);
        cp.add(plist);
        cp.add(pcbox);
        cp.add(minlist);
    }

    public void actionPerformed(ActionEvent zdarzenie) { // obsługa zdarzenia
        if (zdarzenie.getSource() == plist) {
            dane.add(tekst.getText());
            lista.setListData(dane);
            lista.ensureIndexIsVisible(dane.size() - 1);
        } else 
            if (zdarzenie.getSource() == pcbox) {
            cBox.addItem(tekst.getText()); // dodaje do comboboksa
        } else 
            if (zdarzenie.getSource() == minlist) {
            dane.remove(tekst.getText());
            lista.setListData(dane);
            lista.ensureIndexIsVisible(dane.size() - 1);
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        int wybor = e.getFirstIndex();
        if (wybor < dane.size()) {
        tekst.setText((String) dane.elementAt(wybor));
        }
    }

    public void itemStateChanged(ItemEvent e) {
        tekst.setText((String) e.getItem());
    }
}