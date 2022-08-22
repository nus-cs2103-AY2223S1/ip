package duke;

public class Todo extends Task {
    
    Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String SaveString() {
        return String.format("T | %s | %s\n", super.isDone ? "1" : "0",
                super.description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
