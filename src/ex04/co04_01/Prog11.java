package ex04.co04_01;

import javax.swing.*;

public class Prog11 {

    public static void main(String[] args) {
        JFrame f = new JFrame("Moje pierwsze Okno");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // zamkniecie okna nie powoduje zakonczenia programu - to trzeba dorobic
        f.setSize(300, 300);

        f.setVisible(true); // po ustawieniu parametrów pokazuje okno
        
        JFrame f2 = new JFrame("Moje pierwsze Okno");
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // zamkniecie okna nie powoduje zakonczenia programu - to trzeba dorobic
        f2.setSize(300, 300);

        f2.setVisible(true); // po ustawieniu parametrów pokazuje okno
    }
}
