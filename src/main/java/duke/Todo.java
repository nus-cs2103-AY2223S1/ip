package duke;

/**
 *
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return String.format("T | %s | %s", getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            return this.description.equals(((Todo) o).description);
        }
        return false;
    }
}
