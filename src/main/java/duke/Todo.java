package duke;

public class Todo extends Task {

    public Todo(String taskName, boolean isDone) {
        super(taskName.trim(), isDone);
    }

    @Override
    public String taskToFileString() {
        return " T " + "| " + (this.done ? "1 " : "0 ") + "| " + this.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + (this.done ? "[X] " : "[ ] ") + this.taskName;
    }
}
