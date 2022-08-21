public class Deadline extends Task {

    private String deadline;

    public Deadline(String task) {
        super(task.substring(9, task.indexOf('/') - 1));
        this.deadline = task.substring(task.indexOf('/') + 3);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
