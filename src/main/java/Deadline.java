public class Deadline extends Task {

    protected String by;
    private final static String ICON = "D";

    public Deadline(String description, String by) {
        super(description, ICON);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]" + "[%s] " + super.toString() + " (by: " + by + ")", super.getStatusIcon());
    }
}
