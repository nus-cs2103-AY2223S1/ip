public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.description, this.dueDate);
    }
}
