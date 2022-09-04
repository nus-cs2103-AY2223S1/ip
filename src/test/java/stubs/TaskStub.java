package stubs;

import java.time.LocalDateTime;

import command.Commands;
import henry.Task;

public class TaskStub extends Task {

    private boolean testIsDone;

    public TaskStub(boolean isDone) {
        super(Commands.TODO, "test", LocalDateTime.MAX);
        this.testIsDone = isDone;
    }

    public void setComplete(boolean status) {
        this.isDone = status;
    }

    private String getStatusIcon() {
        return testIsDone ? "[X]" : "[ ]"; // mark done task with X
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " stub";
    }
}
