package tasks;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toDataString() {
        return String.format("[T] | %d | %s",
                isMarked() ? 1 : 0,
                getName());
    }
}
