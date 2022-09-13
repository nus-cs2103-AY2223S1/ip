package deku.task;

import java.util.List;

import deku.DekuExceptions;

/**
 * Task type, on a certain day
 */
public class Event extends Task {
    public Event(List<String> task) throws DekuExceptions {
        super(task, "event", "E");
    }

}
