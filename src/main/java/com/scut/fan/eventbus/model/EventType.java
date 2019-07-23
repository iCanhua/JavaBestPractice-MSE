package com.evolve.schedule.eventbus.model;

import java.util.HashSet;
import java.util.Set;

public class EventType {

  /**
   * this event need DistributeTask message
   */
  public static final int RUN_TASK = 2;

  public static final int EXECUTE_INVALID = 3;

  public static final int START_EXECUTE = 4;
  /**
   * this event need ExecuteContext message
   */
  public static final int CONTEXT_RETURN = 5;
  /**
   * this event need ExecuteDetail message
   */
  public static final int SUBMIT_EXECUTE_UNIT = 6;

  /**
   * this event need ExecuteDetail message
   */
  public static final int EXECUTE_FINISH = 7;

  /**
   * this event need ExecuteDetail message
   */
  public static final int EXECUTE_STATE_CHANGE = 8;

  public static final int NEW_EXE_TASK = 9;

  public static final int EXECUTE_TIMEOUT =10;

  private static Set<Integer> blockEvents = new HashSet<>();

  static {
    blockEvents.add(2);
    blockEvents.add(1);
    blockEvents.add(6);
    blockEvents.add(8);
    blockEvents.add(9);
  }

  public static String getDescription(int event) {
    String description = "";
    switch (event) {
      case RUN_TASK:
        description = "page processor find a new target request";
        break;
      case EXECUTE_INVALID:
        description = "find a new request to crawl";
        break;
      case START_EXECUTE:
        description = "an execute unit start to execute";
        break;
      case CONTEXT_RETURN:
        description = "an execute response";
        break;
      case SUBMIT_EXECUTE_UNIT:
        description = "an new unit to the system ";
        break;
      case EXECUTE_FINISH:
        description = "execute unit in finish its job";
        break;
      case EXECUTE_STATE_CHANGE:
        description = "execute switch its state";
        break;
      case NEW_EXE_TASK:
        description = "new target request";
        break;
      case EXECUTE_TIMEOUT:
        description = "execute unit time out";
        break;
      default:
        break;
    }
    return description;
  }

  public static boolean isBlockEvent(Integer event) {
    if (blockEvents.contains(event)) {
      return true;
    } else {
      return false;
    }
  }
}
