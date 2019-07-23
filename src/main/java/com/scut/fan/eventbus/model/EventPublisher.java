package com.evolve.schedule.eventbus.model;


import com.evolve.schedule.eventbus.EventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
/**
 * 发布订阅模式
 */
public class EventPublisher implements EventHandler {

  private static Logger log = LoggerFactory.getLogger(EventPublisher.class);

  private EventPublisher(){}

  private static EventPublisher instance = new EventPublisher();

  public static EventPublisher getInstance(){
    return instance;
  }

  public void handle(int eventType, Object eventObject) {
    switch (eventType) {
      default:
        log.error("cannot parse this event, type is {}", EventType.getDescription(eventType));
        break;
    }
    log.debug("parse event done. item id: {}, eventType: {}", EventType.getDescription(eventType));
  }


}
