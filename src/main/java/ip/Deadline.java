package ip;

class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    protected Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
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
