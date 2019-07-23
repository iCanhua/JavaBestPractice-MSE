package com.evolve.schedule.eventbus.loop;


import com.evolve.schedule.ctrlcentre.monitor.Monitor;
import com.evolve.schedule.ctrlcentre.monitor.Monitored;
import com.evolve.schedule.eventbus.model.Message;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 阻塞事件处理
 */
public class BloEventGroup implements Monitored {

  public static final int loopCount = 5;

  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

  private ArrayList<EventLoop> eventLoops;

  private BloEventGroup() {
    eventLoops = new ArrayList<>();
    for (int i=0; i<loopCount; i++){
      eventLoops.add(new EventLoop());
    }
    Monitor.register(this);
  }

  private static BloEventGroup bloEventGroup = new BloEventGroup();

  public static BloEventGroup getInstance(){
    return bloEventGroup;
  }

  public void push(Message task) {
    EventLoop loop =getEventLoop();
    loop.push(task);
    if(!loop.isRunning()){
      cachedThreadPool.submit(loop);
    }
  }

  private EventLoop getEventLoop(){
    EventLoop  minLBLoop =eventLoops.get(0);
    int minSize =minLBLoop.getTaskSize();
    for(EventLoop eventLoop:eventLoops){
      if (eventLoop.getTaskSize()==0){
        return eventLoop;
      }else {

        if(eventLoop.getTaskSize()<minSize){
          minSize = eventLoop.getTaskSize();
          minLBLoop =eventLoop;
        }
      }
    }
    return minLBLoop;
  }

  @Override
  public Object getHealthIndex() {
    String desc ="";
    for(EventLoop loop:eventLoops){
      desc =desc+ "\nBio 的某个loop,状态为"+loop.isRunning()+" 堆积任务大小为："+loop.getTaskSize();
    }
    return desc;
  }
}
