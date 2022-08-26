package duke.task;

public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String inputToTxt() {
        return String.format("T | %s | %s\n",
                (this.isDone ? "1" : "0"),
                this.taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
