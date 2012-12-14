package ex04.ex04_02;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartWindow extends JFrame implements ActionListener {

    JFrame frame;
    ButtonGroup myGroup1;
    ButtonGroup myGroup2;
    
    JLabel myLabel;
    JLabel myLabel1;
    JLabel myLabel2;
    
    JRadioButton rbMaszyna;
    JRadioButton rbNormal;
    
    JRadioButton Player2;
    JButton bStart;
    JButton bInfo;
    JCheckBox cbSound;
    JPanel panel2;
    JButton bPlayerOneName;
    JButton bPlayerTwoName;

    public static void main(String[] a) {
        StartWindow myTest = new StartWindow();
        myTest.startWindow();

    }

    public void startWindow() {
        frame = new JFrame("SwingPong - opcje startowe");
        frame.setResizable(false);
        frame.pack();
        frame.setLocation(500, 250);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        setBackground(Color.YELLOW);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = frame.getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());        

        c.add(panel, BorderLayout.EAST);

        myLabel2 = new JLabel("DRUGI GRACZ:", SwingConstants.CENTER);
        panel.add(myLabel2);
        JPanel p3 = new JPanel();
        panel.setLayout(new FlowLayout());

        myGroup2 = new ButtonGroup();
        Player2 = new JRadioButton(NewProg21.pTwoName);
        myGroup2.add(Player2);
        panel.add(Player2);

        JRadioButton Komputer = new JRadioButton("Komputer      ");
        myGroup2.add(Komputer);
        panel.add(Komputer);

        Player2.setSelected(true);

        c.add(panel, BorderLayout.WEST);
        
        myLabel1 = new JLabel("POZIOM KOMPUTERA:", SwingConstants.CENTER);
        myGroup1 = new ButtonGroup();
        panel.add(myLabel1);       

        rbNormal = new JRadioButton("Normalny");
        myGroup1.add(rbNormal);
        panel.add(rbNormal);

        rbNormal.setSelected(true);

        rbMaszyna = new JRadioButton("Maszyna      ");
        myGroup1.add(rbMaszyna);
        panel.add(rbMaszyna);

        cbSound = new JCheckBox("Dźwięk");
        cbSound.setSelected(true);
        panel.add(cbSound);
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        bStart = new JButton("START GRY");
        bStart.addActionListener(this);
        frame.getRootPane().setDefaultButton(bStart);
        bInfo = new JButton("Informacje");
        bInfo.addActionListener(this);
        
        panel3.add(bStart);
        panel3.add(bInfo);
        c.add(panel3, BorderLayout.SOUTH);

        panel2 = new JPanel();
        panel3.setLayout(new FlowLayout());
        bPlayerOneName = new JButton("Imię gracza 1 - " + NewProg21.pOneName);
        bPlayerOneName.addActionListener(this);
        bPlayerTwoName = new JButton("Imię gracza 2 - " + NewProg21.pTwoName);
        bPlayerTwoName.addActionListener(this);
        panel3.add(bPlayerOneName);
        panel3.add(bPlayerTwoName);
        c.add(panel2);

        frame.pack();
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bPlayerOneName) {
            String playerOneName = JOptionPane.showInputDialog(null,
                    "Podaj imię gracza nr 1:",
                    "SwingPong - imię gracza 1",
                    JOptionPane.QUESTION_MESSAGE);
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
            if (playerTwoName != null) {
                NewProg21.pTwoName = playerTwoName;
                bPlayerTwoName.setText("Imię gracza 2 - " + NewProg21.pTwoName);
                Player2.setText(NewProg21.pTwoName);
            }
        }

        if (e.getSource() == bStart) {
//            ButtonModel b = myGroup1.getSelection();
//            String t = "Not selected";
//            if (b != null) {
//                t = b.getActionCommand();
//            }
//            myLabel.setText(t);

            NewProg21.isStart = true;
            frame.setVisible(false);
        }
    }
}