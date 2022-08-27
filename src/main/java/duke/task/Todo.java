package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public char getType() {
        return 'T';
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
