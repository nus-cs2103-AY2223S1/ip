package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        this.description = description;
        this.isDone = false;
    }
    public ToDo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    @Override
    public String saveStringFormat() {
        return String.format("T | %d | %s", this.isDone? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T] " + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
