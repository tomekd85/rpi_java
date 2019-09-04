/*
 *  Copyright 2018 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: tomaszd
 *
 *  $Id: $
 */

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class KeyboardRPiMover extends KeyAdapter {

  public Map<HashSet<Integer>, Move> pressedKeys = new HashMap<>();
  private static KeyboardRPiMover keyboardRPiMover = new KeyboardRPiMover();

  private void createPressedKeys(){
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_UP)), new Move("N"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_DOWN)), new Move("S"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_LEFT)), new Move("W"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_RIGHT)), new Move("E"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_RIGHT)), new Move("NE"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_LEFT)), new Move("NW"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT)), new Move("SE"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_DOWN, KeyEvent.VK_LEFT)), new Move("SW"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_W)), new Move("N"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_X)), new Move("S"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_A)), new Move("W"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_D)), new Move("E"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_E)), new Move("NE"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_Q)), new Move("NW"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_Z)), new Move("SW"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_C)), new Move("SE"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_W, KeyEvent.VK_A)), new Move("NE"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_W, KeyEvent.VK_D)), new Move("NW"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_S)), new Move("S"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_S, KeyEvent.VK_A)), new Move("SE"));
    pressedKeys.put(new HashSet(Arrays.asList(KeyEvent.VK_S, KeyEvent.VK_D)), new Move("SW"));
  }

  private KeyboardRPiMover(){
    createPressedKeys();
  }

  public static KeyboardRPiMover getInstance(){
    return keyboardRPiMover;
  }


  public static void main(String[] args) {
    JFrame jFrame = new JFrame();
    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    //Display the frame
    jFrame.pack();
    jFrame.setVisible(true);
  }


}
 

