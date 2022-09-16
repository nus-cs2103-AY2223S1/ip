package duke.task;

import duke.task.Task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public boolean needToRemind() {
        return true;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
