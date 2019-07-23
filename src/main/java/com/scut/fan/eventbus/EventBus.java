package com.evolve.schedule.eventbus;

import com.evolve.schedule.eventbus.model.EventPublisher;
import com.evolve.schedule.ctrlcentre.monitor.Monitor;
import com.evolve.schedule.ctrlcentre.monitor.Monitored;
import com.evolve.schedule.eventbus.loop.BloEventGroup;
import com.evolve.schedule.eventbus.loop.NloEventGroup;
import com.evolve.schedule.eventbus.model.Message;
import com.evolve.schedule.eventbus.model.EventType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 消息总线
 * @date 2019年1月9日
 */
@Component
public class EventBus implements Monitored {

  private static Logger log = LoggerFactory.getLogger("event");
  private LinkedBlockingQueue<Message> taskRegistered = new LinkedBlockingQueue<>(); // wait to be handled
  private ConcurrentLinkedQueue<Message> taskPool = new ConcurrentLinkedQueue<>(); // for reuse
  private ConcurrentHashMap<Integer,AtomicInteger> eventCount =new ConcurrentHashMap<>();

  @Autowired
  EventPublisher eventPublisher;

  public EventBus() {
    Monitor.register(this);
  }

  /**
   * add  event to queue tail
   *
   * @param eventType
   * @param toItem
   * @param object
   */
  public void push(Object toItem, int eventType, Object object) {
    if(!(toItem instanceof EventHandler)){
      log.info("can not push an event to an object that is not an eventHandler");
      return;
    }
    Message message = retrieveEvent(eventType, object, (EventHandler) toItem);
    putInBus(message);
  }

  /**
   * add  event to queue tail
   *
   * @param eventType
   * @param object
   */
  public void publish(int eventType, Object object) {
    Message message = retrieveEvent(eventType, object, EventPublisher.getInstance());
    putInBus(message);
  }

  private void putInBus(Message message){
    try {
      taskRegistered.put(message);
    } catch (InterruptedException e) {
      log.error("put item to queue cause an exception", e);
    }
    log.debug("add an event to tail of the queue, item id is {} , type is {}. the queue size is {} now", EventType
        .getDescription(message.eventType), taskRegistered.size());
  }

  private Message retrieveEvent(int eventType, Object object, EventHandler handler) {
    Message spiderEvent = taskPool.poll();
    if(spiderEvent == null) {
      spiderEvent = new Message(eventType, object, handler);
    } else {
      spiderEvent.eventType = eventType;
      spiderEvent.object = object;
      spiderEvent.handler =handler;
    }
    return spiderEvent;
  }

  /**
   * 循环消费需要处理的事件
   */
  public void loop() {
    while (true) {
      try {
        Message spiderEvent = taskRegistered.take();
        log.debug("ready to parse an event, type is {}. the queue size is {} now",EventType.getDescription(spiderEvent.eventType), taskRegistered
            .size());
        if(spiderEvent != null) {
          if(EventType.isBlockEvent(spiderEvent.eventType)){
            BloEventGroup.getInstance().push(spiderEvent);
          }else {
            NloEventGroup.getInstance().push(spiderEvent);
          }
          countEvent(spiderEvent.eventType);
        }
      } catch (Exception e) {
        log.error("parse event cause an exception", e);
      }
    }
  }

  public void releaseEvent(Message spiderEvent) {
    spiderEvent.object = null;
    taskPool.add(spiderEvent);
  }

  public static void eventError(Integer eventType, String itemId, Object object, Exception e){
    String errDesc= "parse event cause an exception ======" + e.toString() + "======";
    if(eventType!=null){
      errDesc = errDesc+eventType ;
    }
    if(itemId!=null){
      errDesc =errDesc+itemId;
    }
    if(object!=null){
      errDesc =errDesc+object.toString();
    }
    log.error(errDesc);
  }

  @Override
  public Object getHealthIndex() {
    String desc ="";
    for(Integer event :eventCount.keySet()){
      desc =desc+"\n事件："+EventType.getDescription(event)+" 该事件共产生次数"+eventCount.get(event);
    }
    return desc;
  }

  private void countEvent(int eventType){
    AtomicInteger count =  eventCount.get(eventType);
    if(count==null){
      count = new AtomicInteger();
      eventCount.put(eventType,count);
    }else {
      count.incrementAndGet();
    }
  }
}
