package objects;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, Boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + getStatus() + " " + getName();
    }
}
