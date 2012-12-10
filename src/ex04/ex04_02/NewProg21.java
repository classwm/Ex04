package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Shot extends Thread {

    NewProg21 okno;
    int ballSpeed = 0 + okno.ballRadius;

    Shot(NewProg21 okno) {
        this.okno = okno;
    }

    public void run() {
        while (okno.takenPoint == 0) {
            okno.prevBallY = okno.ballY;
            okno.prevBallX = okno.ballX;
            okno.ballY += (ballSpeed * okno.ballDirection);
            okno.ballX += okno.ballAngle * okno.ballRadius;
            // System.out.println(okno.ballY);
            if (okno.ballX <= 0 || okno.ballX >= okno.windowWidth) {
                okno.ballAngle = okno.ballAngle * -1;
            }
            
            if (okno.ballY <= 0) {
                okno.takenPoint = 1;
                okno.pOneScore++;
            } else if (okno.ballY >= okno.windowHeight) {
                okno.takenPoint = -1;
                okno.pTwoScore++;
            } else if (okno.ballDirection == -1 && okno.ballY == (okno.pTwoY + okno.ballRadius) && okno.ballX >= (okno.pTwoX - (okno.ballRadius / 2 )) && okno.ballX <= (okno.pTwoX + okno.pWidth + (okno.ballRadius / 2 ))) {
                okno.ballDirection = 1;
                if (okno.ballAngle == 0){
                    
                }
                Toolkit.getDefaultToolkit().beep();
            } else if (okno.ballDirection == 1 && okno.ballY == (okno.pOneY - okno.ballRadius) && okno.ballX >= (okno.pOneX - (okno.ballRadius / 2 )) && okno.ballX <= (okno.pOneX + okno.pWidth + (okno.ballRadius / 2 ))) {
                okno.ballDirection = -1;
                Toolkit.getDefaultToolkit().beep();
            }
            try {
                Thread.sleep(70);
            } catch (Exception e) {
            }
        }
        System.out.println("Player1: " + okno.pOneScore + " - Player2: " + okno.pTwoScore);
        if (okno.takenPoint == 1) {
            okno.servOne = false;
            okno.servTwo = true;
        }
        if (okno.takenPoint == -1) {
            okno.servOne = true;
            okno.servTwo = false;
        }
        
        okno.ballDirection = 0;
        okno.takenPoint = 0;
        okno.isNewServ = true;
    }
}

class MyListener extends MouseAdapter {

    private NewProg21 okno;

    public MyListener(NewProg21 okno) {
        this.okno = okno;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            if (okno.servOne) {
                okno.shot();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getX() < okno.windowWidth - okno.pWidth) {
            okno.prevOneX = okno.pOneX;
            okno.pOneX = e.getX();
            okno.isMoveOne = true;
        }
    }
}

public class NewProg21 extends JFrame implements KeyListener, Runnable {

    int windowWidth = 800, windowHeight = 800;
    int pWidth = 50, pHeight = 10;
    int windowMid = windowWidth / 2, toWindowCenter = windowMid - (pWidth / 2 );
    int pOneX = toWindowCenter, pOneY = (windowHeight - 100), pTwoX = toWindowCenter, pTwoY = (windowHeight - (windowHeight - 100));
    int pOneScore = 0, pTwoScore = 0;
    int prevOneX = 0, prevTwoX = 0, prevBallX = 0, prevBallY = 0;
    int pSpeed = 10;
    boolean isMoveOne = false, isMoveTwo = false, servOne = false, servTwo = true, isNewServ = false;
    static int ballX, ballY, ballRadius = 10, ballDirection = 0, ballAngle = 0;
    int takenPoint = 0;
        
    

    
    public static void main(String[] args) {
        NewProg21 okno = new NewProg21("Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();
        okno.setVisible(true);
        new Thread(okno).start();

        okno.addKeyListener(okno);

        MyListener mListener = new MyListener(okno);
        okno.addMouseListener(mListener);
        okno.addMouseMotionListener(mListener);
    }

    NewProg21(String tytul) {
        super(tytul);
    }

    void init() {
        Container cp = getContentPane();
        setBackground(Color.yellow);
        setSize(windowWidth, windowHeight);
        setResizable(false);
    }

    /**
     * Inicjalizacja strzalu - uruchomienie watka zmieniajacego wspolrzedne
     * strzalu.
     */
    void shot() {
        if (ballDirection == 0) {
            if (servOne) {
                ballX = (pOneX + (pWidth / 2)) - (ballRadius / 2);
                ballY = pOneY - ballRadius;
                ballDirection = -1;
                ballAngle = pOneStartAngle();
                new Shot(this).start();
            } else if (servTwo) {
                ballX = pTwoX + pWidth / 2 - ballRadius / 2;
                ballY = pTwoY + ballRadius;
                ballDirection = 1;
                ballAngle = pTwoStartAngle();
                new Shot(this).start();
            }
        }
    }
    
    
    int pOneStartAngle() { 
        int pOneXmid = pOneX + (pWidth / 2 );
        if (pOneXmid > windowMid) return 1;
        if (pOneXmid < windowMid) return -1; 
        return 0;
    }
    
    int pTwoStartAngle() {  
        int pTwoXmid = pTwoX + (pWidth / 2 );
        if (pTwoXmid > windowMid) return 1;
        if (pTwoXmid < windowMid) return -1; 
        return 0;
    }
            

    void moveP2(int pDirection) {
        if (pDirection == 1 && pTwoX < windowWidth - pWidth) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;
        } else if (pDirection == -1 && pTwoX > 0) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;
        }
    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran

        if (isMoveOne && !servOne) {
            g.clearRect(prevOneX, pOneY, pWidth, pHeight);
            isMoveOne = false;
        }
        if (isMoveOne && servOne) {
            g.clearRect(prevOneX, pOneY - ballRadius, pWidth, pHeight + ballRadius);
            isMoveOne = false;
        }

        if (isMoveTwo && !servTwo) {
            g.clearRect(prevTwoX, pTwoY, pWidth, pHeight);
            isMoveTwo = false;
        }
        if (isMoveTwo && servTwo) {
            g.clearRect(prevTwoX, pTwoY, pWidth, pHeight + ballRadius);
            isMoveTwo = false;
        }
        
        if (isNewServ) {
            g.clearRect(0, 0, windowWidth, windowHeight);
            pOneX = toWindowCenter;
            pTwoX = toWindowCenter;
            isNewServ = false;
        }

        g.fillRect(pTwoX, pTwoY, pWidth, pHeight);
        g.fillRect(pOneX, pOneY, pWidth, pHeight);

        if (ballDirection == 0 && servOne) {
            
            g.fillOval(pOneX + pWidth / 2 - ballRadius / 2, pOneY - ballRadius, ballRadius, ballRadius);
        }

        if (ballDirection == 0 && servTwo) {
            
            g.fillOval(pTwoX + pWidth / 2 - ballRadius / 2, pTwoY + ballRadius, ballRadius, ballRadius);
        }

        if (ballDirection != 0) {
            g.clearRect(prevBallX, prevBallY, ballRadius, ballRadius);
            g.fillOval(ballX, ballY, ballRadius, ballRadius);
        }


    }

    public void run() { // metoda watka odswierzajacego ekran
        while (true) {
            repaint();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            };
        }
    }
//obsluga zdarzen z klawiatury

    public void keyTyped(KeyEvent e) {
        // te metode zostawie jako pusta
    }

    public void keyReleased(KeyEvent e) {
        // te metode zostawie jako pusta
    }

    public void keyPressed(KeyEvent e) { // reaguje jedynie na przycisniecie klawisza
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                moveP2(-1);
                break;
            case KeyEvent.VK_RIGHT:
                moveP2(1);
                break;
            case KeyEvent.VK_SPACE:
                if (servTwo) {
                    shot();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }
} //NewProg21

