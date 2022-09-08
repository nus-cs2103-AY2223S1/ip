package duke;

/**
 * Todo class to model a Todo object.
 */
public class Todo extends Task {

    /**
     * Constructs a new instance of Todo.
     *
     * @param description the Todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation for storage purpose.
     *
     * @return String representation for storage purpose
     */
    @Override
    public String toStorageString() {
        return "T" + super.toStorageString();
    }

    /**
     * Returns a String representation for Todo.
     *
     * @return String representation for Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns true if obj is equal to this instance else false
     * @param obj object to be compared with
     * @return true if obj is equal to this instance else false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Todo) {
            Todo temp = (Todo) obj;
            return temp.description.equals(this.description) && temp.isDone == this.isDone;
        }
        return false;
    }
}
