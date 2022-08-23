public class Deadline extends Task{
    String completeBy;

    public Deadline(String name, String completeBy) {
        super(name);
        this.completeBy = completeBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + completeBy + ")";
    }

    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        return "D | " + doneVar + " | " + super.name + " | " + this.completeBy;
    }
}
