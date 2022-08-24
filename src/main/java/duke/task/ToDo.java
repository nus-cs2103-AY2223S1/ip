package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String fileDescription() {
        return "T | " + super.fileDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
