package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartWindow implements ActionListener {

    ButtonGroup myGroup1 = null;
    ButtonGroup myGroup2 = null;
    JLabel myLebal = null;
    JLabel myLabel1 = null;
    JLabel myLabel2 = null;

    public static void main(String[] a) {
        StartWindow myTest = new StartWindow();
        myTest.createFrame();
        
    }

    public void createFrame() {
        JFrame f = new JFrame("My Radio Buttons");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = f.getContentPane();
        c.setLayout(new BorderLayout());
        myGroup1 = new ButtonGroup();
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        myLabel1 = new JLabel("Poziom komputera:", SwingConstants.CENTER);
        p.add(myLabel1);
        addOption(p, myGroup1, "Retard");
        addOption(p, myGroup1, "Normal");
        addOption(p, myGroup1, "Maszyna      ");
        c.add(p, BorderLayout.EAST);
        
        myLabel2 = new JLabel("Drugi gracz:", SwingConstants.CENTER);
        p.add(myLabel2);
        myGroup2 = new ButtonGroup();
        JPanel p3 = new JPanel();
        p.setLayout(new FlowLayout());
        addOption(p, myGroup2, "Komputer");
        addOption(p, myGroup2, NewProg21.pTwoName);
        
        c.add(p, BorderLayout.WEST);
        
        
        JPanel p1 = new JPanel(); // tworze Panel by na nim umiescic przyciski
        p1.setLayout(new FlowLayout()); // dla p1 ustawiam rozkład FlowLayout
        JButton b1 = new JButton("START GRY");
        JButton b2 = new JButton("Informacje");
        p1.add(b1);
        p1.add(b2); // dodałem przyciski
        c.add(p1, BorderLayout.SOUTH);
        
        JPanel p2 = new JPanel(); // tworze Panel by na nim umiescic przyciski
        p1.setLayout(new FlowLayout()); // dla p1 ustawiam rozkład FlowLayout
        JButton b3 = new JButton("Zmień imię: " + NewProg21.pOneName);
        JButton b4 = new JButton("Zmień imię: " + NewProg21.pOneName);
        p1.add(b3);
        p1.add(b4); // dodałem przyciski
        c.add(p2);
        
        f.pack();
        f.setVisible(true);
        
    }

    public void addOption(JPanel p, ButtonGroup g, String t) {
        JRadioButton b = new JRadioButton(t);
        b.setActionCommand(t);
        p.add(b);
        g.add(b);
    }

    public void actionPerformed(ActionEvent e) {
        ButtonModel b = myGroup1.getSelection();
        String t = "Not selected";
        if (b != null) {
            t = b.getActionCommand();
        }
        myLebal.setText(t);
    }
}