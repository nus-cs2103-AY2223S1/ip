package caca;

import caca.tasks.Task;

/**
 * A TaskStub to be used for testing.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class TaskStub extends Task {

    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String taskType() {
        return "";
    }

    @Override
    public String toFileFormat() {
        return "";
    }
}
