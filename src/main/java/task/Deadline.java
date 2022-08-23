package deku.task;

import deku.DekuExceptions;

import java.util.List;

public class Deadline extends Task{
    public Deadline(List<String> task) throws DekuExceptions {
        super(task, "deadline", "D");
    }
}
