package com.evolve.schedule.eventbus;

public interface EventHandler {
    void handle(int eventType, Object message);
}
