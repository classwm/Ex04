package ex04.ex04_02;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartWindow implements ActionListener {

    JFrame frame;
    
    ButtonGroup myGroup1 = null;
    ButtonGroup myGroup2 = null;
    JLabel myLabel = null;
    JLabel myLabel1 = null;
    JLabel myLabel2 = null;
    
    JPanel panel2;
    
    JButton bPlayerOneName;
    JButton bPlayerTwoName;

    public static void main(String[] a) {
        StartWindow myTest = new StartWindow();
        myTest.createFrame();

    }

    public void createFrame() {
        frame = new JFrame("SwingPong - opcje startowe");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        myLabel1 = new JLabel("POZIOM KOMPUTERA:", SwingConstants.CENTER);
        myGroup1 = new ButtonGroup();
        panel.add(myLabel1);


        JRadioButton Retard = new JRadioButton("Retard");
        myGroup1.add(Retard);
        panel.add(Retard);

        JRadioButton Normal = new JRadioButton("Normal");
        myGroup1.add(Normal);
        panel.add(Normal);

        Normal.setSelected(true);

        JRadioButton Maszyna = new JRadioButton("Maszyna      ");
        myGroup1.add(Maszyna);
        panel.add(Maszyna);

        c.add(panel, BorderLayout.EAST);

        myLabel2 = new JLabel("DRUGI GRACZ:", SwingConstants.CENTER);
        panel.add(myLabel2);
        JPanel p3 = new JPanel();
        panel.setLayout(new FlowLayout());

        myGroup2 = new ButtonGroup();
        JRadioButton Player2 = new JRadioButton(NewProg21.pTwoName);
        myGroup2.add(Player2);
        panel.add(Player2);

        JRadioButton Komputer = new JRadioButton("Komputer");
        myGroup2.add(Komputer);
        panel.add(Komputer);

        Player2.setSelected(true);

        c.add(panel, BorderLayout.WEST);


        JPanel p1 = new JPanel(); 
        p1.setLayout(new FlowLayout()); 
        JButton b1 = new JButton("START GRY");
        JButton b2 = new JButton("Informacje");
        p1.add(b1);
        p1.add(b2); 
        c.add(p1, BorderLayout.SOUTH);

        panel2 = new JPanel(); 
        p1.setLayout(new FlowLayout()); 
        bPlayerOneName = new JButton("Imię gracza 1 - " + NewProg21.pOneName);
        bPlayerOneName.addActionListener(this);
        bPlayerTwoName = new JButton("Imię gracza 2 - " + NewProg21.pTwoName);
        bPlayerTwoName.addActionListener(this);
        p1.add(bPlayerOneName);
        p1.add(bPlayerTwoName); 
        c.add(panel2);

        frame.pack();
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
//        ButtonModel b = myGroup1.getSelection();
//        String t = "Not selected";
//        if (b != null) {
//            t = b.getActionCommand();
//        }
//        myLabel.setText(t);

        if (e.getSource() == bPlayerOneName) {
            String playerOneName = JOptionPane.showInputDialog(null,
                    "Podaj imię gracza nr 1:",
                    "SwingPong - imię gracza 1",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println("Answer: " + playerOneName);
            if (playerOneName != null) {
                NewProg21.pOneName = playerOneName;                
                bPlayerOneName.setText("Imię gracza 1 - " + NewProg21.pOneName);
            }
        }
        if (e.getSource() == bPlayerTwoName) {
            String playerTwoName = JOptionPane.showInputDialog(null,
                    "Podaj imię gracza nr 2:",
                    "SwingPong - imię gracza 2",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println("Answer: " + playerTwoName);
            if (playerTwoName != null) {
                NewProg21.pTwoName = playerTwoName;                
                bPlayerTwoName.setText("Imię gracza 2 - " + NewProg21.pTwoName);
            }
        }
    }
}