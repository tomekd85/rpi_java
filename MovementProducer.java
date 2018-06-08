/*
 *  Copyright 2018 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: tomaszd
 *
 *  $Id: $
 */

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class MovementProducer implements Runnable {
  private BlockingQueue<Move> queue;
  private String []movements = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "STOP"};

  public MovementProducer(BlockingQueue<Move> queue){
      this.queue = queue;
  }


  @Override
  public void run() {
    Random random = new Random();
    for(int i=0; i < 100; i++){
      Move move = new Move(movements[random.nextInt(movements.length)]);
      try {
        System.out.println("Move \""+ move.getMove()+ "\" added to queue");
        queue.put(move);
        Thread.sleep((long) (Math.random() * 100));
      } catch (InterruptedException e){
        e.printStackTrace();
      }
    }
    try {
      queue.put(new Move("Q"));
    } catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}
 

