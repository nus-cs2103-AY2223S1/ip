package deku.task;

import java.util.List;

import deku.DekuExceptions;

/**
 * Task type, no date/time information
 */
public class ToDo extends Task {
    public ToDo(List<String> task) throws DekuExceptions {
        super(task, "todo", "T");
    }
}
