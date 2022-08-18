public class Deadline extends Task {
    private String end;
    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }
    @Override
    public String getStatusIcon() {
        return (isDone ? "[D][X]" : "[D][ ]"); // mark done task with X
    }

    @Override
    public String getDescription() {
        return String.format("%s(%s)", this.description, this.end);
    }

}
