/*
 *  Copyright 2018 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: tomaszd
 *
 *  $Id: $
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class SocketMovementProducer extends MovementProducer{


  public SocketMovementProducer(BlockingQueue<Move> queue) {
    super(queue);
  }

  public void run() {

    try (
        ServerSocket server = new ServerSocket(9000);
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(
            new InputStreamReader(client.getInputStream()));
//        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
    ) {
      System.out.println("Connected..");
      String line = "";
      while ((line = in.readLine()) != null) {
        System.out.println("Received "+ line);
//        TODO: do not split to single chars look at moves
        try {
          queue.put(new Move(line));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
 

