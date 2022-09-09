package Duke;

/**
 * Duke.Task with an associated timing.
 */
public class Events extends Task {

    /**
     * timing of event.
     */
    String timing;

    /**
     * Constructor to initialize class.
     *
     * @param name      name of task
     * @param init      true if initialized from data file
     * @param completed true if task is completed
     * @param timing    time of event
     * @param listSize  current size of list containing all tasks
     */
    public Events(String name, boolean init, boolean completed, String timing, int listSize) {
        super(name, completed);
        this.timing = timing;
        if (!init) {
            addMessage(listSize);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return timing;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskType() {
        return "E";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addMessage(int listSize) {
        return String.format("     Got it. I've added this task:\n" +
                        "     %s\n" +
                        "     Now you have %d tasks in the list.\n" , this, listSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timing);
    }
}
