package task;

/**
 * Encapsulates a task without any date/time attached to it.
 *
 * @author fannyjian
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            Todo obj = (Todo) o;

            if (obj.description.equals(this.description)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
