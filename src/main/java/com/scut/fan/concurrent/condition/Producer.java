package com.scut.fan.concurrent.condition;

import java.util.Random;

public class Producer implements Runnable{

  String producerId;

  ProductPool productPool;

  public Producer(String producerId,ProductPool productPool) {
    this.producerId = producerId;
    this.productPool=productPool;
  }

  public void fillIn() {
    try {
      productPool.putIn(new Random().nextInt());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    while (true){
      fillIn();
      System.out.println("我是消费者"+producerId+"我生产了:");
    }
  }
}
