public class Deadline extends Task{

    protected String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    protected String getBy() {
        return by;
    }

    protected char getType() {
        return 'D';
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.trim() + ")";
    }
}
