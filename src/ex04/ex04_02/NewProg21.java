package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class Shot extends Thread {

    NewProg21 okno;
    int ballSpeed = 0 + okno.ballDiameter;
    int computerX = 0;
    boolean computeAI = false;
    boolean computerStop = false;
    int threadSleepTime = 60;

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
                if (okno.playerComputer) {
                    computeAI = true;
                    computerStop = false;
                }
                if (okno.ballAngle == 0) {
                    if (okno.ballX > (okno.pOneX + (okno.pWidth / 2)) + (okno.ballDiameter / 5)) {
                        okno.ballAngle = 1;
                    } else if (okno.ballX < (okno.pOneX + (okno.pWidth / 2)) - (okno.ballDiameter)) {
                        okno.ballAngle = -1;
                    }
                }
                Toolkit.getDefaultToolkit().beep();
            }

            if (okno.playerComputer && okno.ballDirection == -1 && !computerStop) {
                if (computeAI) {
                    pongAIxy();
                    System.out.println("Computer X: " + computerX);
                }

                if (okno.pTwoX + (okno.pWidth / 2) > computerX) {
                    okno.prevTwoX = okno.pTwoX;
                    okno.pTwoX -= ballSpeed / 2;
                    okno.isMoveTwo = true;
                } else if (okno.pTwoX + (okno.pWidth / 2) < computerX) {
                    okno.prevTwoX = okno.pTwoX;
                    okno.pTwoX += ballSpeed / 2;
                    okno.isMoveTwo = true;
                }
                if (okno.pTwoX <= 5 || okno.pTwoX >= okno.windowWidth - okno.pWidth - 5) {
                    computerStop = true;
                } else if (computerX > okno.pTwoX + okno.ballDiameter && computerX < okno.pTwoX + (okno.pWidth - okno.ballDiameter)) {
                    computerStop = true;
                }
                System.out.println("PTwoX = " + okno.pTwoX);
            }

            if (okno.playerComputer && okno.ballDirection == 1) {

                if (okno.pTwoX + (okno.pWidth / 2) > okno.windowWidth / 2) {
                    okno.prevTwoX = okno.pTwoX;
                    okno.pTwoX -= ballSpeed / 2;
                    okno.isMoveTwo = true;
                } else if (okno.pTwoX + (okno.pWidth / 2) < okno.windowWidth / 2) {
                    okno.prevTwoX = okno.pTwoX;
                    okno.pTwoX += ballSpeed / 2;
                    okno.isMoveTwo = true;
                }
            }
            try {
                if (!okno.playerComputer) {
                    threadSleepTime = 60;
                } else {
                    threadSleepTime = 40;
                }
                do {
                    Thread.sleep(threadSleepTime);
                } while (okno.gamePause);
            } catch (Exception ex) {
                System.out.println("Wyjątek w Shot.run :)");
                System.out.println(ex);
            }
        }
        if (okno.takenPoint == 1) {
            okno.servOne = true;
            okno.servTwo = false;
        }
        if (okno.takenPoint == -1) {
            okno.servOne = false;
            okno.servTwo = true;
        }

        okno.ballDirection = 0;
        okno.takenPoint = 0;
        okno.isNewServ = true;
        if (okno.playerComputer && okno.servTwo) {
            okno.shot();
        }
    }

    public void pongAIxy() {

        int computerY = okno.pTwoY;
        int targetX = okno.ballX;
        int targetY = okno.ballY;
        int targetAngle = okno.ballAngle;
        int targetDiameter = okno.ballDiameter;
        int targetSpeed = ballSpeed;
        int targetDirection = okno.ballDirection;

        while (targetY != computerY) {
            targetY += (targetSpeed * targetDirection);
            targetX += targetAngle * targetDiameter;
            if (targetX <= 0 + targetDiameter || targetX >= okno.windowWidth - targetDiameter) {
                targetAngle = targetAngle * -1;
            }
        }
        computerX = targetX;
        computeAI = false;
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
        if (!okno.gamePause) {
            if (e.getX() < okno.windowWidth - okno.pWidth) {
                okno.prevOneX = okno.pOneX;
                okno.pOneX = e.getX();
                okno.isMoveOne = true;
            }
        }
    }
}

public class NewProg21 extends JFrame implements KeyListener, Runnable {

    protected static int windowWidth = 800, windowHeight = 800;
    protected int pWidth = 50, pHeight = 10;
    protected int windowMid = windowWidth / 2, toWindowCenter = windowMid - (pWidth / 2);
    protected int pOneX = toWindowCenter, pOneY = (windowHeight - 100), pTwoX = toWindowCenter, pTwoY = (windowHeight - (windowHeight - 100));
    protected static int pOneScore = 0, pTwoScore = 0;
    protected int prevOneX = 0, prevTwoX = 0, prevBallX = 0, prevBallY = 0;
    protected int pSpeed = 12;
    protected boolean isMoveOne = false, isMoveTwo = false, servOne = false, servTwo = true, isNewServ = false;
    static boolean playerComputer = false, gamePause = false, gameOver = false;
    protected static int ballX, ballY, ballDiameter = 10, ballDirection = 0, ballAngle = 0;
    protected int takenPoint = 0;

    public static void main(String[] args) {
        NewProg21 okno = new NewProg21("Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init();

        ScoreWindow score = new ScoreWindow();
        score.initScore();

        JPanel pane = (JPanel) score.getContentPane();
        score.setVisible(true);
        new Thread(score).start();
        score.ballTimer.start();

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
            } else if (servTwo && !playerComputer) {
                ballX = pTwoX + pWidth / 2 - ballDiameter / 2;
                ballY = pTwoY + ballDiameter;
                ballDirection = 1;
                ballAngle = pTwoStartAngle();
                new Shot(this).start();
            } else if (servTwo && playerComputer) {
                System.out.println("Serwis CompBefore");
                servComputer();
                System.out.println("Serwis CompAfter");
                ballX = pTwoX + pWidth / 2 - ballDiameter / 2;
                ballY = pTwoY + ballDiameter;
                ballDirection = 1;
                ballAngle = pTwoStartAngle();
                new Shot(this).start();
            }
        }
    }

    void servComputer() {
        isNewServ = true;
        Random r = new Random();
        int whereToGoX;
        
        whereToGoX = r.nextInt(windowWidth - 5 + 1) + 5;
        System.out.println("Serwis:" + whereToGoX);
        
        if (pTwoX < whereToGoX) {
            while (pTwoX < whereToGoX) {
                prevTwoX = pTwoX;
                pTwoX++;
                isMoveTwo = true;
            }
        } else if (pTwoX > whereToGoX) {
            while (pTwoX > whereToGoX) {
                prevTwoX = pTwoX;
                pTwoX--;
                isMoveTwo = true;
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
        if (!gamePause) {
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

    public void run() { // metoda wątku odświeżającego ekran
        while (true) {
            repaint();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
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
                if (!playerComputer) {
                    moveP2(-1);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!playerComputer) {
                    moveP2(1);
                }
                break;
            case KeyEvent.VK_SPACE:
                if (!playerComputer && servTwo) {
                    shot();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (!gamePause) {
                    gamePause = true;
                    System.out.println("Pause true!");
                    ScoreWindow.isGamePaused = true;
                    (new PauseWindow()).pauseWindow();
                } else if (gamePause) {
                    gamePause = false;
                    System.out.println("Pause false!");
                    ScoreWindow.isGamePaused = false;
                }
                break;
        }
    }
} //NewProg21