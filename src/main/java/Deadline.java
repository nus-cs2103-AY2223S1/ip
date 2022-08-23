public class Deadline extends Task {
    private String end;
    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }
    public Deadline(String description, Boolean isDone, String end) {
        super(description, isDone);
        this.end = end;
    }
    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][X]" : "[D][ ]"); // mark done task with X
    }

    @Override
    public String getDescription() {
        return String.format("%s (%s)", this.description, this.end);
    }

    public String toString() {
        String status = isDone ? "Done  " : "UnDone";
        return String.format("Deadline  | %s | %s | %s", status, super.getDescription(), this.end);
    }
}
