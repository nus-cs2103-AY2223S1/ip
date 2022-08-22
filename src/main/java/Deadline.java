public class Deadline extends Task{
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public String getDescription() {
        return super.description;
    }

    public String getDeadlineBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
