
package ex04.ex04_02;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class StartWindow  {
   JFrame myFrame = null;
   public static void main(String[] a) {
      (new StartWindow()).test();
   }
   private void test() {
       
       int messageType = JOptionPane.QUESTION_MESSAGE;
      String[] options = {"Java", "C++", "VB", "PHP", "Perl"};
      int code = JOptionPane.showOptionDialog(myFrame, 
         "What language do you prefer?", 
         "Option Dialog Box", 0, messageType, 
         null, options, "PHP");
      System.out.println("Answer: "+code);
   }
}
//      myFrame = new JFrame("showOptionDialog() Test");
//      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      Container myPane = myFrame.getContentPane();
//      JButton myButton = new JButton("Show");
//      myButton.addActionListener(this);
//      myPane.add(myButton);
//      myFrame.pack();
//      myFrame.setVisible(true);
   
   // implements ActionListener
//   }
//   public void actionPerformed(ActionEvent e) {
//      int messageType = JOptionPane.QUESTION_MESSAGE;
//      String[] options = {"Java", "C++", "VB", "PHP", "Perl"};
//      int code = JOptionPane.showOptionDialog(myFrame, 
//         "What language do you prefer?", 
//         "Option Dialog Box", 0, messageType, 
//         null, options, "PHP");
//      System.out.println("Answer: "+code);
//   }
//}