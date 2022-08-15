public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void done() {
        this.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n " + this.toString());
    }

    @Override
    public void notDone() {
        this.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n " + this.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}