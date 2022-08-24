class Deadline extends Task {
    protected String by;

    public Deadline(String itself, String by) {
        super(itself);
        this.by = by;
    }

    @Override
    public String writeToFile() {
        return "D|" + super.writeToFile() + "|" + by.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
