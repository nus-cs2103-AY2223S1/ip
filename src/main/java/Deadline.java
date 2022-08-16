class Deadline extends Task {
    private String deadline;
    private static final String PREFIX = "by ";
    private static final String SPLIT = "/by ";

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    static Deadline createDeadline(ParsedData data) throws DukeException {
        if (data.description.length() == 0)
            throw new EmptyDescriptionException("deadline");

        if (data.additionalInfo.length() == 0 || !data.additionalInfo.startsWith(PREFIX))
            throw new EmptyTimeException("deadline", SPLIT);
        return new Deadline(data.description, data.additionalInfo.substring(3));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
