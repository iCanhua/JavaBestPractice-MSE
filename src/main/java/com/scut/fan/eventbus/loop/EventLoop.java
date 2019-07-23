package com.evolve.schedule.eventbus.loop;



import com.evolve.schedule.eventbus.EventBus;
import com.evolve.schedule.utils.SpringUtil;
import com.evolve.schedule.eventbus.model.Message;
import com.evolve.schedule.eventbus.model.EventType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class EventLoop implements Runnable {

  private static Logger log = LoggerFactory.getLogger("event");

  private volatile boolean isRunning =false;

  private LinkedBlockingQueue<Message> taskRegistered =new LinkedBlockingQueue<>();
      // wait to be handled

  public EventLoop() {
  }

  /**
   * 循环消费需要处理的事件
   */
  public void loop() throws InterruptedException {
    while (true) {
      Message message = taskRegistered.take();
      try {
        log.debug("ready to parse an event, type is {}. the queue size is {} now", EventType
            .getDescription(message.eventType), taskRegistered.size());
        if(message != null) {
          long curTime = System.currentTimeMillis();
          message.getHandler().handle(message.eventType, message.object);
          if(System.currentTimeMillis() - curTime > 2000){
            log.warn("======= there is some event exceed than 2s =======:"+message.toString());
          }
        }
      } catch (Exception e) {
        log.error("event loop parse event cause an exception", e);
      }
      releaseEvent(message);
    }
  }

  /**
   * add  event to queue tail
   *
   */
  public void push(Message task) {
    try {
      taskRegistered.put(task);
    } catch (InterruptedException e) {
      log.error("put item to queue cause an exception", e);
    }
    log.debug("add an event to tail of the queue, task desc is {} ,the queue size is {} now", task.toString(), taskRegistered
        .size());
  }

  private void releaseEvent(Message spiderEvent) {
    SpringUtil.getBean(EventBus.class).releaseEvent(spiderEvent);
  }

  @Override
  public void run() {
    try {
      isRunning =true;
      loop();
    } catch (InterruptedException e) {
      e.printStackTrace();
      isRunning =false;
    }
  }

  public boolean isRunning(){
    return isRunning;
  }

  public int getTaskSize(){
    return taskRegistered.size();
  }
}
