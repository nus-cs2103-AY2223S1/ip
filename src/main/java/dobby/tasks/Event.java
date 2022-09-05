package dobby.tasks;

/**
 * Event is a class which is a task to happen at a certain date.
 */
public class Event extends Task {
    private String date;

    /**
     * Constructor of the Event class.
     *
     * @param task Description of event task
     * @param date Date of event task
     */
    public Event(String task, String date) {
        super(task);
        this.date = date;
    }

    /**
     * Constructor of the Event class.
     *
     * @param task Description of event task
     * @param date Date of event task
     * @param isDone Completion status of event task
     */
    public Event(String task, String date, boolean isDone) {
        super(task, isDone);
        this.date = date;
    }

    /**
     * Returns the formatted Event task to be saved in the file.
     *
     * @return String of deadline task.
     */
    @Override
    public String toPrint() {
        return "E" + super.toPrint() + " | " + date;
    }

    /**
     * Returns the formatted Event task to be printed out.
     *
     * @return String of deadline task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
