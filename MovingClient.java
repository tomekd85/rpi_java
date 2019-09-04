/*
 *  Copyright 2018 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: tomaszd
 *
 *  $Id: $
 */

import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class MovingClient extends KeyAdapter {

  PrintWriter out;
  Socket echoSocket;
  BufferedReader stdIn;
  Set<Integer> pressedKeys = new HashSet<>();

  public MovingClient() throws IOException {
//    try (
        echoSocket = new Socket("localhost", 9000);
        out = new PrintWriter(echoSocket.getOutputStream(), true);
//        BufferedReader in =
//            new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        stdIn =
            new BufferedReader(new InputStreamReader(System.in));
//    ) {
//      String userInput;
//      while ((((userInput = stdIn.readLine())) != null) &&
//          !userInput.equals("")) {
//        out.println(userInput);
//        System.out.println(in.readLine());
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
//    System.out.println("You typed a key "+ e.getKeyChar());
//    out.println(e.getKeyChar());
  }

  @Override
  public void keyPressed(KeyEvent e) {
//    System.out.println("You pressed a key "+ e.getKeyChar());
//    out.println(e.getKeyChar());
    updatePressedKeys(true, e);
    sendMove();
  }

  @Override
  public void keyReleased(KeyEvent e) {
//    System.out.println("You released a key "+ e.getKeyChar());
//    out.println("-" + e.getKeyChar());
    updatePressedKeys(false, e);
    sendMove();
  }

  void updatePressedKeys(boolean isAdd, KeyEvent key){
    if(isAdd){
      pressedKeys.add(key.getKeyCode());
    } else {
      pressedKeys.remove(key.getKeyCode());
    }
    for(Integer integer: pressedKeys) {
      System.out.println(integer);
    }
  }

  void sendMove(){
    KeyboardRPiMover keyboardRPiMover = KeyboardRPiMover.getInstance();
    Move move = keyboardRPiMover.pressedKeys.get(pressedKeys);
    if(move != null) {
      out.println(move.getMove());
      System.out.println("You sent move "+ move.getMove());
    }
  }

  public static void main(String[] args) {

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JPanel mainPanel = new JPanel();
    JTextField textField = new JTextField("Press arrow keys or QWEADZXC keys to move robot");
    textField.setEditable(false);
    JButton button = new JButton("Click Me and Move");
    mainPanel.add(textField);
    mainPanel.add(button);

    frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    frame.setSize(400, 400);
    button.grabFocus();
    frame.setVisible(true);

    try {
      MovingClient mc = new MovingClient();
      button.addKeyListener(mc);
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
 

