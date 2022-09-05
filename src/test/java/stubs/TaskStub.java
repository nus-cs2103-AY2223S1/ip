package stubs;

import java.time.LocalDateTime;

import command.Commands;
import henry.Task;

/**
 * Stub for the Task class
 */
public class TaskStub extends Task {

    private final boolean testIsDone;

    /**
     * Creates a new TaskStub object. Currently only supports testing
     * for the isDone() method.
     * @param isDone whether the task is marked complete
     */
    public TaskStub(boolean isDone) {
        super(Commands.TODO, "test", LocalDateTime.MAX);
        this.testIsDone = isDone;
    }

    public void setComplete(boolean status) {
        super.setComplete(status);
    }

    private String getStatusIcon() {
        return testIsDone ? "[X]" : "[ ]"; // mark done task with X
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " stub";
    }
}
