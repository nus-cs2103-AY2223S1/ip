public class Deadline extends Task{
    protected String timing;

    public Deadline(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "]" +  super.toString() + " (by: " + timing + ")";
    }
}
