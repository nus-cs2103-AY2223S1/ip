package duke.task;

/**
 * Event class that inherits task
 */
public class ToDo extends Task {

    /**
     * Constructor of ToDo
     *
     * @param description what the task contains
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of toDo
     *
     * @return string that is representation of toDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString().substring(0, 4)
                + super.toString().substring(9);
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline || o instanceof Event) {
            return 1;
        } else {
            return this.description.compareTo(o.description);
        }
    }
}
