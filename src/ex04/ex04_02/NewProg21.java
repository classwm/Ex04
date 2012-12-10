package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Shot extends Thread {

    NewProg21 okno;
    int ballSpeed = 0 + okno.ballDiameter;

    Shot(NewProg21 okno) {
        this.okno = okno;
    }

    public void run() {
        while (okno.takenPoint == 0) {
            okno.prevBallY = okno.ballY;
            okno.prevBallX = okno.ballX;
            okno.ballY += (ballSpeed * okno.ballDirection);
            okno.ballX += okno.ballAngle * okno.ballDiameter;

            if (okno.ballX <= 0 + okno.ballDiameter || okno.ballX >= okno.windowWidth - okno.ballDiameter) {
                okno.ballAngle = okno.ballAngle * -1;
            }

            if (okno.ballY <= 0) {
                okno.takenPoint = 1;
                okno.pOneScore++;
            } else if (okno.ballY >= okno.windowHeight) {
                okno.takenPoint = -1;
                okno.pTwoScore++;
            } else if (okno.ballDirection == -1 && okno.ballY == (okno.pTwoY + okno.ballDiameter) && okno.ballX >= (okno.pTwoX - (okno.ballDiameter / 2)) && okno.ballX <= (okno.pTwoX + okno.pWidth + (okno.ballDiameter / 2))) {
                okno.ballDirection = 1;
                if (okno.ballAngle == 0) {
                    if (okno.ballX > (okno.pTwoX + (okno.pWidth / 2)) + (okno.ballDiameter / 2)) {
                        okno.ballAngle = 1;
                    } else if (okno.ballX < (okno.pTwoX + (okno.pWidth / 2)) - (okno.ballDiameter / 2)) {
                        okno.ballAngle = -1;
                    }
                }
                Toolkit.getDefaultToolkit().beep();
            } else if (okno.ballDirection == 1 && okno.ballY == (okno.pOneY - okno.ballDiameter) && okno.ballX >= (okno.pOneX - (okno.ballDiameter / 2)) && okno.ballX <= (okno.pOneX + okno.pWidth + (okno.ballDiameter / 2))) {
                okno.ballDirection = -1;
                if (okno.ballAngle == 0) {
                    if (okno.ballX > (okno.pOneX + (okno.pWidth / 2)) + (okno.ballDiameter / 2)) {
                        okno.ballAngle = 1;
                    } else if (okno.ballX < (okno.pOneX + (okno.pWidth / 2)) - (okno.ballDiameter / 2)) {
                        okno.ballAngle = -1;
                    }
                }
                Toolkit.getDefaultToolkit().beep();
            }
            try {
                Thread.sleep(60);
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

    static int windowWidth = 800, windowHeight = 800;
    int pWidth = 50, pHeight = 10;
    int windowMid = windowWidth / 2, toWindowCenter = windowMid - (pWidth / 2);
    int pOneX = toWindowCenter, pOneY = (windowHeight - 100), pTwoX = toWindowCenter, pTwoY = (windowHeight - (windowHeight - 100));
    static int pOneScore = 0, pTwoScore = 0;
    int prevOneX = 0, prevTwoX = 0, prevBallX = 0, prevBallY = 0;
    int pSpeed = 12;
    boolean isMoveOne = false, isMoveTwo = false, servOne = false, servTwo = true, isNewServ = false;
    static int ballX, ballY, ballDiameter = 10, ballDirection = 0, ballAngle = 0;
    int takenPoint = 0;

    public static void main(String[] args) {
        NewProg21 okno = new NewProg21("Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();

        scoreWindow score = new scoreWindow();
        score.initScore();

        JPanel pane = (JPanel) score.getContentPane();
        score.setVisible(true);
        new Thread(score).start();

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

    static class scoreWindow extends JFrame implements Runnable {
        int scoreWidth = 300, scoreHeight = 180;
        int scorePositionX = windowWidth + 100, scorePositionY = 200; 
        public void paint(Graphics g) {
            g.clearRect(0, 0, 300, 180);
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif",
                    Font.BOLD, 20));
            g.drawString("PLAYER 1:", 15, 60);
            g.drawString("PLAYER 2:", 180, 60);
            g.setFont(new Font("SansSerif",
                    Font.BOLD, 100));
            g.drawString(Integer.toString(pOneScore), 50, 150);
            g.drawString(Integer.toString(pTwoScore), 220, 150);

        }

        public void run() { // metoda watka odswierzajacego 2ekran
            while (true) {
                repaint();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            } // 
        } // score run

        void initScore() {
            setTitle("Wynik Super Gry :)");
            pack();
            setLocation(scorePositionX, scorePositionY);
            setSize(scoreWidth, scoreHeight);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setAlwaysOnTop(true);
            setResizable(false);
            setBackground(Color.YELLOW);
        }
    } //scoreWindow

    void init() {
        Container cp = getContentPane();
        setBackground(Color.BLACK);
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
                ballX = (pOneX + (pWidth / 2)) - (ballDiameter / 2);
                ballY = pOneY - ballDiameter;
                ballDirection = -1;
                ballAngle = pOneStartAngle();
                new Shot(this).start();
            } else if (servTwo) {
                ballX = pTwoX + pWidth / 2 - ballDiameter / 2;
                ballY = pTwoY + ballDiameter;
                ballDirection = 1;
                ballAngle = pTwoStartAngle();
                new Shot(this).start();
            }
        }
    }

    int pOneStartAngle() {
        int pOneXmid = pOneX + (pWidth / 2);
        if (pOneXmid > windowMid) {
            return 1;
        }
        if (pOneXmid < windowMid) {
            return -1;
        }
        return 0;
    }

    int pTwoStartAngle() {
        int pTwoXmid = pTwoX + (pWidth / 2);
        if (pTwoXmid > windowMid) {
            return 1;
        }
        if (pTwoXmid < windowMid) {
            return -1;
        }
        return 0;
    }

    void moveP2(int pDirection) {
        if (pDirection == 1 && pTwoX < windowWidth - pWidth - 3) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;
        } else if (pDirection == -1 && pTwoX > 3) {
            prevTwoX = pTwoX;
            pTwoX += (pDirection * pSpeed);
            isMoveTwo = true;
        }
    }

    public void paint(Graphics g) { // metoda odrysowujaca ekran

        g.setColor(Color.WHITE);

        if (isMoveOne && !servOne) {
            g.clearRect(prevOneX, pOneY, pWidth, pHeight);
            isMoveOne = false;
        }
        if (isMoveOne && servOne) {
            g.clearRect(prevOneX, pOneY - ballDiameter, pWidth, pHeight + ballDiameter);
            isMoveOne = false;
        }

        if (isMoveTwo && !servTwo) {
            g.clearRect(prevTwoX, pTwoY, pWidth, pHeight);
            isMoveTwo = false;
        }
        if (isMoveTwo && servTwo) {
            g.clearRect(prevTwoX, pTwoY, pWidth, pHeight + ballDiameter);
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

            g.fillOval(pOneX + pWidth / 2 - ballDiameter / 2, pOneY - ballDiameter, ballDiameter, ballDiameter);
        }

        if (ballDirection == 0 && servTwo) {

            g.fillOval(pTwoX + pWidth / 2 - ballDiameter / 2, pTwoY + ballDiameter, ballDiameter, ballDiameter);
        }

        if (ballDirection != 0) {
            g.clearRect(prevBallX, prevBallY, ballDiameter, ballDiameter);
            g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
        }
    } // paint

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

