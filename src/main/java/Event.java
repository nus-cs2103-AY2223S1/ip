public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        numberOfTasks++;
    }

    @Override
    public String addedString() {
        return "Got it. I've added this task:\n " + this.toString() + "\nNow you have " + this.numberOfTasks + " tasks in the list.";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
