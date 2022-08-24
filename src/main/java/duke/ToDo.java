package duke;

import duke.Task;

public class ToDo extends Task {
    public ToDo(String name, boolean initialComplete) {
        super(name, initialComplete);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String toFileRepresentation() {
        return "T" + "|" + (this.isComplete() ? "1" : "0") + "|" + this.getName();
    }
}
