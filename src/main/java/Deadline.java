public class Deadline extends Task{
    protected String by;
    public Deadline(String taskName,String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.taskName + " (by: " + this.by + ")";
    }
}
