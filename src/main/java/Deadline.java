public class Deadline  extends Task{
    public String deadline;

    public Deadline(String[] command) {
        super(command[0]);
        deadline = command[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}