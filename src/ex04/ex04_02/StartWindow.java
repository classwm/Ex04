package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    JRadioButton rbPlayer2;
    JRadioButton rbKomputer;
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

    /**
     * Okno startowe z ustawianiem opcji gry
     *
     */
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
        rbPlayer2 = new JRadioButton(NewProg21.pTwoName);
        myGroup2.add(rbPlayer2);
        rbPlayer2.setActionCommand("Player2");
        panel.add(rbPlayer2);

        rbKomputer = new JRadioButton("Komputer      ");
        myGroup2.add(rbKomputer);
        rbKomputer.setActionCommand("Komputer");
        panel.add(rbKomputer);

        rbKomputer.setSelected(true);

        c.add(panel, BorderLayout.WEST);

        myLabel1 = new JLabel("POZIOM KOMPUTERA:", SwingConstants.CENTER);
        myGroup1 = new ButtonGroup();
        panel.add(myLabel1);

        rbNormal = new JRadioButton("Normalny");
        myGroup1.add(rbNormal);
        rbNormal.setActionCommand("Normalny");
        panel.add(rbNormal);

        rbNormal.setSelected(true);

        rbMaszyna = new JRadioButton("Maszyna      ");
        myGroup1.add(rbMaszyna);
        rbMaszyna.setActionCommand("Maszyna");
        panel.add(rbMaszyna);

        cbSound = new JCheckBox("Dźwięk");
        cbSound.setSelected(true);
        cbSound.addActionListener(this);
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

    /**
     * Obsługa zdarzeń okienka startowego
     *
     * @param ev
     */
    @Override
    public void actionPerformed(ActionEvent ev) {

        if (ev.getSource() == bPlayerOneName) { // zmiana imienia gracza 1
            String playerOneName = JOptionPane.showInputDialog(null,
                    "Podaj imię gracza nr 1:",
                    "SwingPong - imię gracza 1",
                    JOptionPane.QUESTION_MESSAGE);
            if (playerOneName != null) {
                NewProg21.pOneName = playerOneName;
                bPlayerOneName.setText("Imię gracza 1 - " + NewProg21.pOneName);
            }
        }
        if (ev.getSource() == bPlayerTwoName) { // zmiana imienia gracza 2
            String playerTwoName = JOptionPane.showInputDialog(null,
                    "Podaj imię gracza nr 2:",
                    "SwingPong - imię gracza 2",
                    JOptionPane.QUESTION_MESSAGE);
            if (playerTwoName != null) {
                NewProg21.pTwoName = playerTwoName;
                bPlayerTwoName.setText("Imię gracza 2 - " + NewProg21.pTwoName);
                rbPlayer2.setText(NewProg21.pTwoName);
            }
        }
        if (ev.getSource() == bInfo) { // wczytanie i wyświetlenie pliku z opisem
            String f = System.getProperty("user.dir") + "/src/ex04/ex04_02/sp-info.txt";            
            File file = new File(f);
            JTextArea text = new JTextArea(30, 70);
            text.setLineWrap(true);  
            JScrollPane jsp = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String s = new String();
                while ((s = in.readLine()) != null) {
                    text.append(s + "\n\r");
                }
                text.setEditable(false);
                jsp.getViewport().add(text);
                JOptionPane.showMessageDialog(bInfo, jsp);
            } catch (FileNotFoundException ex) {
                System.out.println("Brak pliku.");
            } catch (IOException ex) {
                System.out.println("Błąd wejścia-wyjścia.");
            }
        }

        if (ev.getSource() == bStart) { // start gry, poprzedzony sprawdzeniem ustawień opcji 
            ButtonModel b2 = myGroup2.getSelection();
            String t2 = "Not selected";
            if (b2 != null) {
                t2 = b2.getActionCommand();
            }
            ButtonModel b1 = myGroup1.getSelection();
            String t1 = "Brak wyboru";
            if (b1 != null) {
                t1 = b1.getActionCommand();
            }            
            if (t1.equals("Maszyna")) {
                NewProg21.cSpeedFactor = 1;
            }
            if (t2.equals("Komputer")) {
                NewProg21.playerComputer = true;
            }
            if (!cbSound.isSelected()) {
                NewProg21.isSound = false;
            }
            NewProg21.isStart = true;
            frame.setVisible(false);
        }
    }
}