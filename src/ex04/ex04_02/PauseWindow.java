package ex04.ex04_02;

import javax.swing.*;

public class PauseWindow {

    JFrame myFrame = null;

    public static void main(String[] a) {
        (new PauseWindow()).pauseWindow();
    }

    protected void pauseWindow() {

        int messageType = JOptionPane.QUESTION_MESSAGE;
        String[] options = {"Zakończ grę", "Powrót do gry", "Player 2 = Komputer"};
        if (NewProg21.playerComputer) {
            options[2] = "Player2 = Człowiek";
        }
        int buttonCode = JOptionPane.showOptionDialog(myFrame,
                "Co chcesz zrobić?","Option Dialog Box", 0, messageType, null, options, "Powrót do gry");
        System.out.println("Wciśnięto: " + buttonCode);
        if (buttonCode == 0) {
            System.exit(0);
        } else if (buttonCode == 1) {
            NewProg21.gamePause = false;
            ScoreWindow.isGamePaused = false;
        } else if (buttonCode== 2) {
            if (!NewProg21.playerComputer) {
                NewProg21.playerComputer = true;
                NewProg21.pTwoName = "Komputer";
            } else if (NewProg21.playerComputer) {
                NewProg21.playerComputer = false;
                NewProg21.pTwoName = "Player 2";
            }            
            NewProg21.gamePause = false;            
            ScoreWindow.isGamePaused = false;
        }
    }
}