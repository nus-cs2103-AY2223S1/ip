package duke.task;

import duke.task.Task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public boolean needToRemind() {
        return !isDone;
    }

    @Override
    public String getOutput() {
        return String.format("T | %d | %s", isDone ? 1 : 0, name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
