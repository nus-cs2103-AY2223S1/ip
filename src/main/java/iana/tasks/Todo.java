package iana.tasks;
public class Todo extends Task {
    public Todo(String task, boolean isCompleted) {
        super(task, "todo", isCompleted);
    }

    @Override
    public String toFileData() {
        return "T | " + super.toFileData();
    }

    @Override
    public String toString() { 
        return String.format("[T]%s", super.toString());
    }
}
