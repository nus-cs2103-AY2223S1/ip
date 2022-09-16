package bro.task;

/**
 * Todo class.
 */
public class Todo extends Task {

    /**
     * Constructs the class with the description.
     * @param description Gets the description from the super class.
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

