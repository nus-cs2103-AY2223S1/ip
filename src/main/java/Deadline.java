public class Deadline extends Task {
    private String deadline;

    Deadline(String title, String deadline) {
        this(title, false, deadline);
    }

    Deadline(String title, boolean isCompleted, String deadline) {
        super(title, isCompleted);
        this.deadline = deadline;
    }

    public static Deadline fromSaveFormat(String saveFormat) throws IllegalArgumentException {
        final String[] args = saveFormat.split(";;");
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect save format: " + saveFormat);
        }
        return new Deadline(args[2], args[1].equals("1"), args[0]);
    }

    @Override
    public String toSaveFormat() {
        return String.format("D;;%s;;%s", deadline, super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("‼ %s (by %s)", super.toString(), deadline);
    }
}
