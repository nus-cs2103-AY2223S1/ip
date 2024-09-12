package deku.task;

import java.util.List;

import deku.DekuExceptions;

/**
 * Task type, before a certain day
 */
public class Deadline extends Task {
    public Deadline(List<String> task) throws DekuExceptions {
        super(task, "deadline", "D");
    }
}
