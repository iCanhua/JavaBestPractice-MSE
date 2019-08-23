package com.scut.fan.concurrent.condition;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 线程安全的产品池
 */
public class ProductPool {

  private int size;

  private Queue<Object> pool = new LinkedList<Object>();

  private int productSize =0;

  private int consumeSize =0;



  private ProductPool(Integer size){
    this.size=size;
  }

  public void putIn(Object product) throws InterruptedException {
    synchronized (this){
      while (pool.size()>=size){
        System.out.println("当前产品满，请稍后：size"+pool.size());
        wait();
      }
      pool.offer(product);
      productSize++;
      System.out.println("共生产了："+productSize);
      notifyAll();
    }
  }

  public Object takeOut() throws InterruptedException {
    synchronized (this){
      while (pool.size()==0){
        System.out.println("当前没有产品，请稍后："+pool.size());
        wait();
      }
      Object product = pool.poll();
      consumeSize++;
      System.out.println("共消费了："+consumeSize);
      notifyAll();
      return product;
    }
  }

  public static void main(String[] args) {
    ProductPool productPool = new ProductPool(10);
    Consumer consumer1= new Consumer("消费着小北鱼",productPool);
    Consumer consumer2= new Consumer("消费着罗莎",productPool);
    Producer producer = new Producer("生产者范灿华",productPool);
    Thread thread1 = new Thread(consumer1);
    thread1.start();
    Thread thread2 = new Thread(consumer2);
    thread2.start();
    Thread thread3 = new Thread(producer);
    thread3.start();
  }

}
