public class Deadline extends Task {

    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: " + this.by + ")", this.getTaskType(), super.toString());
    }
}
