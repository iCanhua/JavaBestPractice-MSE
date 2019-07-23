package com.evolve.schedule.eventbus.loop;


import com.evolve.schedule.ctrlcentre.monitor.Monitor;
import com.evolve.schedule.ctrlcentre.monitor.Monitored;
import com.evolve.schedule.eventbus.model.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NloEventGroup implements Monitored {

  ExecutorService cachedThreadPool = Executors.newSingleThreadScheduledExecutor();

  EventLoop loop = new EventLoop();

  private NloEventGroup() {
    Monitor.register(this);
  }

  private static NloEventGroup nloEventGroup = new NloEventGroup();

  public static NloEventGroup getInstance(){
    return nloEventGroup;
  }

  public void push(Message task) {
    loop.push(task);
    if(!loop.isRunning()){
      cachedThreadPool.submit(loop);
    }
  }

  @Override
  public Object getHealthIndex() {
    return "\nNloEventGroup 未处理数为："+loop.getTaskSize();
  }
}
