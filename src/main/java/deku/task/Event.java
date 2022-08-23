package deku.task;

import deku.DekuExceptions;

import java.util.List;

/**
 * Task type, on a certain day
 */
public class Event extends Task {
    public Event(List<String> task) throws DekuExceptions {
        super(task, "event", "E");
    }

}
