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

    /**
     * Converts this Todo to a String to be stored in a .txt file.
     *
     * @return the String representation of this Todo
     */
    @Override
    public String toFileRepresentation() {
        return String.format("T|%d|%s", this.isComplete() ? 1 : 0, this.getName());
    }
}
