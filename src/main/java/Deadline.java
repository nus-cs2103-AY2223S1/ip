class Deadline extends Task {
    private String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    static Deadline createDeadline(ParsedData data) {
        /*
         * TODO
         * Add error checking here
         */

        return new Deadline(data.description, data.additionalInfo.substring(3));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
