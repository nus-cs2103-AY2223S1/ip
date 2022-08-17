package main.java;

// got date
public class Deadlines extends Task{

    protected String by;

    public Deadlines(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
