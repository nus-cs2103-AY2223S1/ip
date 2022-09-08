package duke.tasks;

/**
 * The Todo class encapsulates a task to be done.
 */
public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of the type and isDone attributes of a Todo object.
     * @return String representation of the status of a Todo object.
     */
    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[T][X]";
        } else {
            return "[T][ ]";
        }
    }

    /**
     * Returns string representation of a Todo object.
     * @return String representation of a Todo object.
     */
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Todo td = (Todo) obj;
            return this.description.equals(td.description);
        }
        return false;
    }
}
