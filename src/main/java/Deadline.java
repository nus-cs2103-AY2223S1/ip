public class Deadline extends Task {

    private String by;

    public Deadline(String name, boolean isDone, String by) throws DukeException {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[D]" + temp + " (by: " + by + ")";
    }


}
