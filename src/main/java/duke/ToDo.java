package duke;

public class ToDo extends Task {
    protected String type = "[T]";

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return type + super.toString();
    }

    @Override
    public String toFile() {
        String isDone = "0";
        if (super.isDone) {
            isDone = "1";
        }
        return String.format("T|%s|%s\n", isDone, super.description);
    }
}
