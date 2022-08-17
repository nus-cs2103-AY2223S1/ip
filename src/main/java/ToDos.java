package main.java;

// todos work the same as tasks
public class ToDos extends Task {

    public ToDos(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
