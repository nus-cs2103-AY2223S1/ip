package entities;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
