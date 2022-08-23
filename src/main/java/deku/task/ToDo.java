package deku.task;

import deku.DekuExceptions;

import java.util.List;

/**
 * Task type, no date/time information
 */
public class ToDo extends Task {
    public ToDo(List<String> task) throws DekuExceptions {
        super(task, "todo", "T");
    }
}
