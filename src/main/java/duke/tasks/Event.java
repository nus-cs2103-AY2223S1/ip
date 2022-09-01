package duke.tasks;

/**
 * Event implements methods for Event objects.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class Event extends Task {

    private final String deadline;
    private final String taskDesc;

    /**
     * Creates a new Event object.
     *
     * @param taskDesc description of the event task
     * @param date deadline of the event task
     */
    public Event(String taskDesc, String deadline) {
        super(taskDesc);
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    /**
     * Fetches the type of task for identification during encoding and decoding for storage.
     *
     * @return a Character 'E' representing Event
     */
    @Override
    public char getTaskType() {
        return 'E';
    }

    /**
     * Fetches the description of the task.
     *
     * @return the description of the task
     */
    @Override
    public String getDesc() {
        return this.taskDesc + "|" + this.deadline;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + deadline + ")";
    }
}
