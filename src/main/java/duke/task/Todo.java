package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStringToSave() {
        return this.isDone
                ? "T | 1 | " + description
                : "T | 0 | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
