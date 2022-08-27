package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String fileStatus() {
        return "T | " + super.fileStatus();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
