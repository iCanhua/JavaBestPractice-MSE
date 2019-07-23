package com.evolve.schedule.eventbus.model;


import com.evolve.schedule.eventbus.EventHandler;

public class Message {
  public Integer eventType;
  public Object object;
  public transient EventHandler handler;

  public Message(int eventType, Object object, EventHandler handler) {
    this.eventType = eventType;
    this.object = object;
    this.handler =handler;
  }

  public EventHandler getHandler() {
    return handler;
  }

  public void setHandler(EventHandler handler) {
    this.handler = handler;
  }

  public String toString(){
    String desc= "event desc --";
    if(eventType!=null){
      desc = desc+eventType+" " ;
    }
    return desc;
  }

}
