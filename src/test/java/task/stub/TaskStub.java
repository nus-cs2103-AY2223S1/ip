package task.stub;

import task.Task;

public class TaskStub extends Task {
    public TaskStub() {
        super("Testing!");
    }

    @Override
    public String encode() {
        return this.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof TaskStub) {
            return this.getIsMarked() == ((TaskStub) o).getIsMarked();
        }

        return false;
    }

    @Override
    public String toString() {
        return "Testing!";
    }
}
