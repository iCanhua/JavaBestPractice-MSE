package com.scut.fan.concurrent.dbcp;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 数据库连接池类，获取方法具有超时效应，应用wait的超时等待模式
 * Created by FAN on 2016/7/1.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool=new LinkedList<Connection>();
    public ConnectionPool(int initialSize){
        if(initialSize>0){
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放连接
     * @param connection
     */
    public void releaseConnection(Connection connection){
        if (connection!=null){
            synchronized (pool){
                //连接释放后需要进行通知，这样其他消费者能够感知到连接池中已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 该方法在规定时间内（毫秒级）获取一个连接，如果该连接在时间内获取失败，那么返回空
     * @param mills 超时时间
     * @return 超时返回null，否则返回一个Connection对象
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
            //完全超时
            if (mills<=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis()+ mills;
                long remaining=mills;
                //采用迭代方式，超时等待的去尝试竞争线程池资源
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining=future-System.currentTimeMillis();
                }
                Connection result=null;
                if (!pool.isEmpty()){
                    result=pool.removeFirst();
                }
                return result;
            }
        }
    }


}
