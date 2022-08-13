public class Deadline extends Task{

    private String deadline;

    public Deadline(String name, boolean done, String deadline) throws TaskNoNameException {
        super(name, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
