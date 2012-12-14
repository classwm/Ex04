package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class Shot extends Thread {

    NewProg21 okno;
    int ballSpeed = 0 + okno.ballDiameter; // szybkość ruchu piłki
    int computerX = 0; // współrzędna X, na ktrórej ma się zatrzymać gracz strwo. przez komputer
    static boolean computeAI = false; // flaga  czy obliczać ruch piłki
    static boolean computerStop = false; // flaga czy zatrzymać ruch gracza sterow. przez komputer
    int threadSleepTime = 60;

    Shot(NewProg21 okno) {
        this.okno = okno;
    }

    /**
     * Wątek obsługi zmiany pozycji piłki, sprawdzenie kolizji, zmiana pozycji
     * gracza sterowanego przez komputer
     */
    public void run() {

        while (okno.takenPoint == 0) {
            okno.prevBallY = okno.ballY;
            okno.prevBallX = okno.ballX;
            okno.ballY += ballSpeed * okno.ballDirection;
            okno.ballX += ballSpeed * okno.ballAngle;

            if (okno.ballX <= 0 + okno.ballDiameter || okno.ballX >= okno.windowWidth - okno.ballDiameter) {
                okno.ballAngle = okno.ballAngle * -1;
            }

            if (okno.ballY <= 0) {
                okno.takenPoint = 1;
                okno.pOneScore++;
                if (okno.pOneScore == 100) {
                    okno.pOneScore = 0;
                    okno.pTwoScore = 0;
                }
            } else if (okno.ballY >= okno.windowHeight) {
                okno.takenPoint = -1;
                okno.pTwoScore++;
                if (okno.pTwoScore == 100) {
                    okno.pTwoScore = 0;
                    okno.pOneScore = 0;
                }
            } else if (okno.ballDirection == -1 && okno.ballY == (okno.pTwoY + okno.ballDiameter) && okno.ballX >= (okno.pTwoX - (okno.ballDiameter / 2)) && okno.ballX <= (okno.pTwoX + okno.pWidth + (okno.ballDiameter / 2))) {
                okno.ballDirection = 1;

                if (okno.ballAngle == 0) {
                    if (okno.ballX > (okno.pTwoX + (okno.pWidth / 2)) + (okno.ballDiameter / 2)) {
                        okno.ballAngle = 1;
                    } else if (okno.ballX < (okno.pTwoX + (okno.pWidth / 2)) - (okno.ballDiameter / 2)) {
                        okno.ballAngle = -1;
                    }
                }
                if (okno.isSound) {
                    Toolkit.getDefaultToolkit().beep();
                }
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
                if (okno.isSound) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            if (okno.playerComputer && okno.ballDirection == -1 && !computerStop) {
                if (computeAI) {
                    pongAIxy();
                }

                if (okno.pTwoX + (okno.pWidth / 2) > computerX) {
                    okno.prevTwoX = okno.pTwoX;
                    okno.pTwoX -= ballSpeed / okno.cSpeedFactor;
                    okno.isMoveTwo = true;
                } else if (okno.pTwoX + (okno.pWidth / 2) < computerX) {
                    okno.prevTwoX = okno.pTwoX;
                    okno.pTwoX += ballSpeed / okno.cSpeedFactor;
                    okno.isMoveTwo = true;
                }
                if (okno.pTwoX <= 5 || okno.pTwoX >= okno.windowWidth - okno.pWidth - 5) {
                    computerStop = true;
                } else if (computerX > okno.pTwoX + okno.ballDiameter && computerX < okno.pTwoX + (okno.pWidth - okno.ballDiameter)) {
                    computerStop = true;
                }
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
                if (!okno.playerComputer) { //regulowanei szybkości piłki dla człowieka i komputera jako 2 gracza
                    threadSleepTime = 60;
                } else {
                    threadSleepTime = 40;
                }
                do {
                    Thread.sleep(threadSleepTime); // zatrzymanie wątku podczas przerwy w grze
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
        if (okno.playerComputer && okno.servTwo) { // wywołanie serwu komputera
            okno.shot();
        }
    }

    /**
     * Obliczenie współrzędnej X piłki na linii gracza 2
     */
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
} //pongAIxy

/**
 * Obsługa listenera myszki
 *
 */
class MyListener extends MouseAdapter {

    private NewProg21 okno;

    public MyListener(NewProg21 okno) {
        this.okno = okno;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            if (okno.servOne) {
                okno.shot();
            }
        }
    }

    @Override
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

    protected static int windowWidth = 800, windowHeight = 800; // rozmiar pola gry
    protected int pWidth = 50, pHeight = 10; // rozmiar paletek
    protected int windowMid = windowWidth / 2, toWindowCenter = windowMid - (pWidth / 2);
    protected int pOneX = toWindowCenter, pOneY = (windowHeight - 100), pTwoX = toWindowCenter, pTwoY = (windowHeight - (windowHeight - 100));
    protected static int pOneScore = 0, pTwoScore = 0; // wyniki graczy
    protected int prevOneX = 0, prevTwoX = 0, prevBallX = 0, prevBallY = 0; // zmienne umożliwiające kasowanie poprzedniej pozycji paletki
    protected int pSpeed = 12; // szybkośc gracza 2
    static int cSpeedFactor = 2; // wspołczynnik sprawności komputera
    protected boolean isMoveOne = false, isMoveTwo = false, servOne = true, servTwo = false, isNewServ = false; // flagi do ustalenia aktualnego stanu gry
    static boolean playerComputer = false, gamePause = false, isStart = false, isSound = true; // flagi stanu gry
    protected static int ballX, ballY, ballDiameter = 10, ballDirection = 0, ballAngle = 0;
    protected int takenPoint = 0;
    static String pOneName = "Player 1", pTwoName = "Player 2";

    public static void main(String[] args) {
        NewProg21 okno = new NewProg21("SwingPong -- Super Gra -- :)...");
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.init(); // init pola gry

        StartWindow start = new StartWindow();
        start.startWindow(); //uruchomienie okienka z wyborem opcji gry

        while (!isStart) {  // pętla oczekiwania na naciśnięcie START
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

        ScoreWindow score = new ScoreWindow();
        score.initScore(); // init okienka z wynikiem

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
    } //main

    NewProg21(String tytul) {
        super(tytul);
    }

    /**
     * Inicjalizacja parametrów okienka z polem gry
     */
    void init() {
        Container cp = getContentPane();
        setBackground(Color.BLACK);
        setSize(windowWidth, windowHeight);
        setResizable(false);
    } // ini

    /**
     * Inicjalizacja strzału - uruchomienie wątka zmieniającego współrzędne
     * strzału.
     */
    void shot() {
        if (ballDirection == 0) {
            if (servOne) {
                ballX = (pOneX + (pWidth / 2)) - (ballDiameter / 2);
                ballY = pOneY - ballDiameter;
                ballDirection = -1;
                ballAngle = pOneStartAngle();
                if (playerComputer) {
                    Shot.computeAI = true;
                    Shot.computerStop = false;
                }
                new Shot(this).start();
            } else if (servTwo && !playerComputer) {
                ballX = pTwoX + pWidth / 2 - ballDiameter / 2;
                ballY = pTwoY + ballDiameter;
                ballDirection = 1;
                ballAngle = pTwoStartAngle();
                new Shot(this).start();
            } else if (servTwo && playerComputer) {
                isNewServ = false;
                servComputer();
                ballX = pTwoX + pWidth / 2 - ballDiameter / 2;
                ballY = pTwoY + ballDiameter;
                ballDirection = 1;
                ballAngle = pTwoStartAngle();
                new Shot(this).start();
            }
        }
    } // shot()

    /**
     * Obsługa rozpoczęcia gry przez komputer
     */
    void servComputer() {
        isNewServ = true;
        Random r = new Random();
        int whereToGoX;

        whereToGoX = r.nextInt(windowWidth - (pWidth + 5));
        if (whereToGoX < 10) {
            whereToGoX = 10;
        }

        if (pTwoX < whereToGoX) {
            while (pTwoX < whereToGoX) {
                try {
                    Thread.sleep(20);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                prevTwoX = pTwoX;
                pTwoX += 10;
                isMoveTwo = true;
            }
        } else if (pTwoX > whereToGoX) {
            while (pTwoX > whereToGoX) {
                try {
                    Thread.sleep(20);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                prevTwoX = pTwoX;
                pTwoX -= 10;
                isMoveTwo = true;
            }
        }
    } // servComputer()

    /**
     * Ustalenie w którą stornę poleci piłka przy serwie gracza 1, zależnie od
     * pozycji paletki względem osi okna
     *
     * @return zwraca (1) gdy paletka jest po prawej stronie okienka i piłka
     * ruszy w prawo, (-1) gdy w lewo
     */
    int pOneStartAngle() {
        int pOneXmid = pOneX + (pWidth / 2);
        if (pOneXmid > windowMid) {
            return 1;
        }
        if (pOneXmid < windowMid) {
            return -1;
        }
        return 0;
    } // pOneStartAngle()

    /**
     * Ustalenie w którą stornę poleci piłka przy serwie gracza 2, zależnie od
     * pozycji paletki względem osi okna
     *
     * @return zwraca (1) gdy paletka jest po prawej stronie okienka i piłka
     * ruszy w prawo, (-1) gdy w lewo
     */
    int pTwoStartAngle() {
        int pTwoXmid = pTwoX + (pWidth / 2);
        if (pTwoXmid > windowMid) {
            return 1;
        } else if (pTwoXmid < windowMid) {
            return -1;
        }
        return 0;
    } // pTwoStartAngle()

    /**
     * Poruszanie górną paletką za pomocą klawiatury
     *
     * @param pDirection kierunek ruchu (1) w prawo, (-1) w lewo
     */
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
    } // moveP2

    /**
     * Rysowanie okna z polem gry, kasowanie tylko obszarów piłki i paletek
     *
     * @param g
     */
    public void paint(Graphics g) {

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
            } catch (Exception ex) {
                System.out.println(ex);
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
                    ScoreWindow.isGamePaused = true;
                    (new PauseWindow()).pauseWindow();
                } else if (gamePause) {
                    gamePause = false;
                    ScoreWindow.isGamePaused = false;
                }
                break;
        }
    }
} //NewProg21