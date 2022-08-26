public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    protected String getStorageString() {
        String parStr = super.getStorageString();
        return String.format("%s|%s|%s", "D", parStr, getBy());
    }

    @Override
    public String toString() {
        String parStr = super.toString();
        return String.format("[D]%s (by: %s)", parStr, getBy());
    }
}
