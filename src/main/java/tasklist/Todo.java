package tasklist;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }
    }

    @Override
    public String toStringText() {
        return "T | " + this.description;
    }
}
