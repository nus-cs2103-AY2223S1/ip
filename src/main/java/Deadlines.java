public class Deadlines extends Task {

    private String deadline;

    public Deadlines(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",this.getDone() ? "X" : " ", this.getTask(), this.deadline);
    }
}
