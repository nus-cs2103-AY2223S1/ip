public class Deadline extends Task {
    String by;
    Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("%s%s(by: %s)", "[D]", super.toString(), this.by);
    }
}
