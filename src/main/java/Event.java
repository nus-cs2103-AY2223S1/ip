public class Event extends Task {

    private String deadline;

    public Event(String task) {
        super(task.substring(6, task.indexOf('/') - 1));
        this.deadline = task.substring(task.indexOf('/') + 3);
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + deadline + ")";
    }
}
