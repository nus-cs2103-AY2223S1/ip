package duke;

public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description.trim());
        this.isDone = isDone;
        Task.taskCount++;
    }

    @Override
    public String toString() {
        return String.format("T | %s | %s", this.getStatusIcon(), this.description);
    }

}


