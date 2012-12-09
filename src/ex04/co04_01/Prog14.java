package ex04.co04_01;

import java.awt.*;
import javax.swing.*;

public class Prog14 extends JFrame {

    public static void main(String[] args) {
        Prog14 okno = new Prog14("Okno z czym sie da");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true); // main sie konczy, a okno dalej zyje samo
    }

    Prog14(String tytul) {
        super(tytul);
    }

    @Override
      public void paint(Graphics g) {
        super.paint(g);
    }      
    
    void init() {
        setSize(500, 300);
        setBackground(Color.cyan);
        setResizable(false);

        Container cp = getContentPane(); // chce dodawac komponenty - pobieram kontener
        cp.setBackground(Color.YELLOW);        
        cp.setLayout(new FlowLayout());
        
        JTextField text1 = new JTextField(25);
        cp.add(text1);
        text1.setText("Oto text");
        
        JButton b1 = new JButton("Pierwszy");
        cp.add(b1);
        
        JButton b2 = new JButton("Drugi");
        cp.add(b2);
        
        JTextArea obszar = new JTextArea("Tu jaki napis", 12, 20);
        // cp.add(obszar);
        cp.add(new JScrollPane(obszar));
        JLabel lab = new JLabel("Ala ma kota");
        cp.add(lab);
        
        JCheckBox cb1 = new JCheckBox("1");
        JCheckBox cb2 = new JCheckBox("2");
        JCheckBox cb3 = new JCheckBox("3");
        cp.add(cb1);
        cp.add(cb2);
        cp.add(cb3);
    }
}