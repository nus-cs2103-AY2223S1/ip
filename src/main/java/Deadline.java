public class Deadline extends Task {
    protected String by;

    public Deadline(String desc, String by) throws EmptyDescException {
        super(desc);
        this.by = by;
        if (desc.equals("") || by.equals("")) {
            throw new EmptyDescException("empty");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
