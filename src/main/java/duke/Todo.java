package duke;

public class Todo extends Task {

    /**
     * Keep track of things to do.
     *
     * @param action
     */

    public Todo(String action) {
        super(action);
    }

    @Override
    public String getWriteString() {
        return String.format("T | %s", super.getWriteString());
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}