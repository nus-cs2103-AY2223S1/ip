package duke;

public class Deadline extends Task {

    protected Deadline(String name, String dueDate) {
        super(name, dueDate);
        type = "D";
    }

    @Override
    public String stringType() {
        return "deadline";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getDate());
    }
}
