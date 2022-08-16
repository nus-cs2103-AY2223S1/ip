public class Deadline extends Task {
    public String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    @Override
    public String print() {
        return String.format("[D]%s (by: %s)", super.print(), this.deadline);
    }
}
