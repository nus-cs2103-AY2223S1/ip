public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.numberOfTasks += 1;
    }

    public String getSaveData() {
        return "D|" + (super.isDone ? 1 : 0) + "|" + super.description + "|" + this.by;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + "(by: " + this.by + ")");
    }
}
