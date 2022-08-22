public class Deadline extends Task {
    private String deadline;

    public Deadline(boolean isDone, String text, String deadline, boolean isPrinting) {
        super(isDone, text, isPrinting);
        this.deadline = deadline;
        if (isPrinting) {
            System.out.println(this.toString());
        }
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}