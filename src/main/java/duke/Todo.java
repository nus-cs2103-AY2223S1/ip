package duke;
public class Todo extends Task {

    public Todo(String item) {
        super(item);
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
