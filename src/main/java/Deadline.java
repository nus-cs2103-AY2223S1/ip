public class Deadline extends Task{

    protected String returnBy;

    public Deadline(String description, String returnBy) {
        this.description = description;
        this.isDone = false;
        this.returnBy = returnBy;
    }

    @Override
    public String toString() {
        return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.returnBy + ")";

    }
}
