/*
 *  Copyright 2018 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: tomaszd
 *
 *  $Id: $
 */

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class Mover implements Runnable{

  private BlockingQueue<Move> queue;;
  private static Mover instance;
  private static Set<Move> currentMove;

  public BlockingQueue<Move> getQueue() {
    return queue;
  }

  private Mover(){
    this.queue = new LinkedBlockingQueue<>();
    this.currentMove = new HashSet<>();
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
    MovementProducer movementProducer = new SocketMovementProducer(mover.getQueue());
    executorService.execute(movementProducer);
    executorService.execute(mover);
    executorService.shutdown();
  }

  @Override
  public void run() {
    Move move;
    while (true) {
      while ((move = queue.poll()) != null) {
        updateCurrentMove(move);
        System.out.format("Move %s received\n", move.getMove());
      }
      doMove();
    }
  }

  private void updateCurrentMove(Move move){
    currentMove.add(move);
  }

  private void doMove(){
    if (currentMove.size() == 1){
      System.out.println("Moving Robot to direction: " + currentMove.iterator().next().getMove());
    }
    currentMove.clear();
  }


}
 

