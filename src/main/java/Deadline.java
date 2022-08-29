public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    String saveStringToFile() {
        return String.format("%s%s\n", super.saveStringToFile(), by);
    }
}