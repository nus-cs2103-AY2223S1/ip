package duke.tasks;

public class Deadline extends Task {

    private final String by;

    public Deadline(String description) {
        super(description.split("/by")[0].trim());
        this.by = description.split("/by")[1].trim();
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getStatusIcon(), description, by);
    }
}
