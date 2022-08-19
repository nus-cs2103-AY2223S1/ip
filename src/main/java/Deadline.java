public class Deadline extends Task{

    String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if(isDone()) {
            return "[D][X] " + getName()+ " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + getName() + " (by: " + this.deadline + ")";
        }
    }
}
