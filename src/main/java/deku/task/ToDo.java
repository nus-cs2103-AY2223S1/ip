package deku.task;

import deku.DekuExceptions;

import java.util.List;

public class ToDo extends Task{
    public ToDo(List<String> task) throws DekuExceptions {
        super(task, "todo","T");
    }
}
