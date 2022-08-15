public class Deadline extends Task{
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[D][X]";
        } else {
            return "[D][ ]";
        }
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description + " (by: " + this.time + ")";
    }
}
