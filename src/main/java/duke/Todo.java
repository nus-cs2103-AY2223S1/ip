package duke;

/** Represents a Todo class. Supports description. */
public class Todo extends Task {
    public Todo(String item) {
        super(item);
    }

    @Override
    public boolean isDated() {
        return false;
    }

    /**
     * Returns String representation of aTodo object.
     * @return String representation
     */



    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
