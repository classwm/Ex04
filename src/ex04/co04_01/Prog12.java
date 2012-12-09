package ex04.co04_01;

import java.awt.*;
import javax.swing.*;

public class Prog12 extends JFrame {

    public static void main(String[] args) {
        Prog12 okno = new Prog12("Moj drugie Okno");
        okno.init(); // inicjujemy
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.setVisible(true);
    }

    Prog12(String tytul) { // to taki maly konstruktor
        super(tytul);
    }

    void init() { // tu nadajemy parametry naszemu oknu
        setSize(500, 500);
        setBackground(Color.blue);
        setResizable(false);
    }

    @Override // z JFrame
    public void paint(Graphics g) {
        // g - obiekt/obszar na ktorym mozemy rysowac, przekazany z naszego okna
        g.drawLine(10, 25, 154, 166);
        g.drawOval(40, 50, 153, 137);
        g.setColor(Color.red);
        g.drawLine(30, 25, 74, 66);
        g.drawOval(70, 50, 53, 37);
        g.fillRect(100, 200, 50, 70);
        g.setColor(Color.pink);
        g.fillRoundRect(300, 200, 50, 70, 30, 50);
        g.fill3DRect(100, 300, 59, 68, true);
        g.setColor(Color.white);
        g.fill3DRect(300, 300, 59, 68, false);
        g.fillArc(400, 300, 150, 170, 80, 50);
        g.setColor(Color.cyan);
        g.setFont(new Font("SansSerif",
                Font.BOLD | Font.ITALIC, 25));
        g.drawString("Nasze drugie okienko", 120, 70);
    }
}