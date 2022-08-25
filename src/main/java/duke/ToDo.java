package duke;

public class ToDo extends Task {

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStorageFormat() {
        char done = isDone ? '1' : '0';
        return "T" + " | " + done + " | " + this.description + "\n";
    }
}
