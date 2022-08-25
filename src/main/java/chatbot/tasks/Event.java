package chatbot.tasks;

/**
 * The Event class is a subclass of Task emulating
 * an event in a real life where there is a date that is
 * tied to the event.
 */
public class Event extends Task {
    public String date;

    public Event(String taskName) throws IndexOutOfBoundsException {
        super(taskName.substring(6, taskName.indexOf(" /at")));
        this.date = taskName.substring(taskName.indexOf(" /at") + 5);
    }

    public Event(String taskName, boolean isComplete, String date) throws IndexOutOfBoundsException {
        super(taskName, isComplete);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }

    @Override
    public String save() {
        return "E | " + this.getStatus() + " | " + this.getTaskName() + " | " + this.date;
    }
}
