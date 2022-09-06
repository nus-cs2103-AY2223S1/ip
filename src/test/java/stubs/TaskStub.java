package stubs;

import java.time.LocalDateTime;

import command.Commands;
import henry.Task;

/**
 * Stub for the Task class
 */
public class TaskStub extends Task {

    private final boolean testIsDone;
    private final String customToString;

    /**
     * Creates a new TaskStub object that supports testing
     * for the isDone() method.
     *
     * @param isDone whether the task is marked complete
     */
    public TaskStub(boolean isDone) {
        super(Commands.DUMMY, "test", LocalDateTime.MAX);
        this.testIsDone = isDone;
        this.customToString = "";
    }

    /**
     * Creates a new TaskStub object with the given input String
     * as the toString() output.
     *
     * @param inputString the String to be returned by toString()
     */
    public TaskStub(String inputString) {
        super(Commands.DUMMY, "test", LocalDateTime.MAX);
        this.testIsDone = false;
        this.customToString = inputString;
    }

    public void setComplete(boolean status) {
        super.setComplete(status);
    }

    private String getStatusIcon() {
        return testIsDone ? "[X]" : "[ ]"; // mark done task with X
    }

    @Override
    public String toString() {
        if (!customToString.equals("")) {
            return customToString;
        }
        return "[T]" + getStatusIcon() + " stub";
    }
}
