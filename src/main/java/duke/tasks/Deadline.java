package duke.tasks;

public class Deadline extends Task {

    private final String by;

    public Deadline(String description) {
        super(description.split("/by")[0]);
        this.by = description.split("/by")[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getStatusIcon(), description, by);
    }
}
