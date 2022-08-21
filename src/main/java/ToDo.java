/**
 * The ToDo class represents a task
 * the user specified that needs to be done.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     * @param description description for the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    public ToDo(int i, String description) {
        super(description);
        if (i == 1) {
            this.markDone();
        }
    }

    @Override
    public String toStore() {
        String status = super.isDone ? "1" : "0";
        String temp = "T" + " | " + status + " | " + super.description;
        return temp;
    }

    /**
     * Overriding method of toString() for ToDo.
     * @return the string representing ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
