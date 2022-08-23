public class Deadline extends Task {
    private String deadline;

    Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
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
