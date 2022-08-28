package bobthebot.tasks;

/**
 * A class representing todo, a type of task.
 * */
public class Todo extends Task {
    /**
     * Constructor for todo.
     * @param description of the deadline.
     * */
    public Todo(String description) {
        super(description.trim());
    }

    /**
     * Returns a String representation of a todo.
     * @return String representation of todo.
     * */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Method which converts an todo to a format for storage.
     * @return String representation of a todo for storage.
     * */
    @Override
    public String toStorageFormat() {
        int done = isDone ? 1 : 0;
        String res = String.format("T | %d | %s", done, taskName);
        return res;
    }
}
