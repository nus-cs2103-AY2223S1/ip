public class Deadline extends Task {

    protected String dueTime;

    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }



    @Override
    public String toString() {
        String output = String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.dueTime);
        return output;
    }
}
