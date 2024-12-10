package frc.robot.tools;

import java.util.HashMap;
import java.util.Set;

public class PriorityHandler<T> {

  private final HashMap<Object, Integer> objectPriorities = new HashMap<Object, Integer>();
  private final HashMap<Object, T> priorityRequests = new HashMap<Object, T>();

  public void register(Object requester, int priority) {
    objectPriorities.put(requester, priority);
  }

  public void setRequest(Object requester, T value) {
    if (isRegistered(requester)) {
      priorityRequests.put(requester, value);
    }
  }

  public void clearRequests() {
    priorityRequests.clear();
  }

  public boolean isRegistered(Object requester) {
    return objectPriorities.containsKey(requester);
  }

  public T getHighestPriorityRequest() {
    Set<Object> requesters = priorityRequests.keySet();
    Object maxPriorityObject = null;
    for (Object requester : requesters) {
      if (maxPriorityObject == null) {
        maxPriorityObject = requester;
      } else if (objectPriorities.get(maxPriorityObject) < objectPriorities.get(requester)) {
        maxPriorityObject = requester;
      }
    }
    return priorityRequests.get(maxPriorityObject);
  }
}
