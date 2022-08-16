class Deadline extends Task {
    protected String by;

    public Deadline(String itself, String by) {
        super(itself);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
