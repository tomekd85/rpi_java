/*
 *  Copyright 2018 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: tomaszd
 *
 *  $Id: $
 */

import java.util.concurrent.*;

public class Mover implements Runnable{

  private BlockingQueue<Move> queue;;
  private static Mover instance;

  public BlockingQueue<Move> getQueue() {
    return queue;
  }

  private Mover(){
    this.queue = new LinkedBlockingQueue<>();
  }

  public static Mover getInstance(){
    if (instance == null){
      instance = new Mover();
    }
    return instance;
  }

  public static void main(String[] args) {
    System.out.println("In main function.");
    Mover mover = Mover.getInstance();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    MovementProducer movementProducer = new MovementProducer(mover.getQueue());
    executorService.execute(movementProducer);
    executorService.execute(mover);
    executorService.shutdown();
  }

  @Override
  public void run() {
    Move move;
    try {
      while (!(move = queue.take()).getMove().equals("Q")){
        System.out.format("Move %s received\n", move.getMove());
        Thread.sleep((int) Math.random() * 100);
      }
    } catch (InterruptedException e){
      e.printStackTrace();
    }
  }
}
 

