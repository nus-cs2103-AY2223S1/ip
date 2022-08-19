package duke.entities;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        String marker = isDone() ? "[X] " : "[ ] ";
        return "[T]" + marker + getDescription();
    }
}