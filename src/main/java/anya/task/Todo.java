package anya.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        return "T | " + doneVar + " | " + super.name;
    }
}
