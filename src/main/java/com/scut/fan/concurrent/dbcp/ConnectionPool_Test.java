package com.scut.fan.concurrent.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟线程并发获取线程池的数据库连接资源
 * Created by FAN on 2016/7/1.
 */
public class ConnectionPool_Test {
    //参数为十个线程池共有的线程数
    static ConnectionPool pool=new ConnectionPool(10);
    //保证所有的ConnectionRunner同时跑,把该倒计时放到每一个类中调用await即可，这里也是CountDownLatch的另类用法
    static CountDownLatch start=new CountDownLatch(1);
    //main线程将会等待所有ConnectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        //线程数量，可以修改线程数量进行观察
        int threadCount=100;
        end=new CountDownLatch(threadCount);
        //一个线程获取20次连接
        int count=20;
        AtomicInteger got=new AtomicInteger();
        AtomicInteger notGot=new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread=new Thread(new ConnectionRunner(count,got,notGot),"ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke:"+(threadCount*count));
        System.out.println("not got connection:"+notGot);
        System.out.println("got connection:"+got);

    }

    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();
            }catch (Exception ex){
            }
            while (count>0){
                try {
                    //从线程池中获取连接，如果1000ms内无法获取到，将返回null
                    //分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection=pool.fetchConnection(1000);
                    if (connection!=null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notGot.incrementAndGet();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }


}
