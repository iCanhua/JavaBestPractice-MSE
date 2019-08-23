package com.scut.fan.concurrent.condition;

public class Consumer implements Runnable{

  String name;
  ProductPool productPool;

  public Consumer(String name,ProductPool productPool){
    this.name=name;
    this.productPool = productPool;
  }

  public void take(){
    try {
      System.out.println(productPool.takeOut());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    while (true){
      take();
      System.out.println("我是消费者"+name+"我消费了:");
    }
  }
}
