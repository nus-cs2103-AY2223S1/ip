package Sakura;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String stringifyTask() {
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.by);
    }

    @Override
    public String toString() {
        return "\u001B[31m(DEADLINE)\u001B[0m" + super.toString() + " (by: " + by + ")";
    }
}
