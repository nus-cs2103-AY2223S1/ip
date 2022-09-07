package caca;

import caca.tasks.Task;

/**
 * A TaskStub to be used for testing.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class TaskStub extends Task {

    private boolean isDone;

    /**
     * Constructor for creating a TaskStub.
     *
     * @param description TaskStub description from user input.
     */
    public TaskStub(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String taskType() {
        return " ";
    }

    @Override
    public String toFileFormat() {
        return "";
    }
}
