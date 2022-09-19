package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {

        super(description, isDone);
    }

    @Override
    public String fileString() {

        return "T" + super.fileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
