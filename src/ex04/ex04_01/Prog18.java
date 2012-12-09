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
        setSize(500, 400);
        Container cp = getContentPane();

        cp.setLayout(new BorderLayout()); // wybieram układacz brzegowy dla całego okna
        
        JTextField text1 = new JTextField(40);
        cp.add(text1, BorderLayout.NORTH);
        text1.setText("Tekst 1");
        JButton b1 = new JButton("Pierwszy");
        // cp.add(b1);
        
        JPanel jp = new JPanel();
        cp.add(jp);  
        jp.setSize(500,150);
        jp.setLayout(new FlowLayout());                                   
        
        JPanel jp2 = new JPanel();
        cp.add(jp2, BorderLayout.EAST);
        jp.setLayout(new GridLayout(3,1));        
        jp.add(b1);
        
        JPanel jp3 = new JPanel();
        cp.add(jp3);
        jp.setLayout(new FlowLayout());
        
        JCheckBox cb1 = new JCheckBox("1");
        jp.add(cb1);
        
        
        
        JCheckBox cb2 = new JCheckBox("2");
        jp2.add(cb2);
        
        JCheckBox cb3 = new JCheckBox("3");
        jp2.add(cb3);
        
        JButton b2 = new JButton("Graj");
        jp.add(b2);
        
        JButton b3 = new JButton("Zatrzymaj");
        jp.add(b3);
        
        
    }
}