public class Deadline extends Task {
    private final String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by %s)", super.toString(), this.dueDate);
    }
}