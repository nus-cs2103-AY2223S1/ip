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
     * @param name task name
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
    public void addMessage(int listSize) {
        System.out.printf(
                "    ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "     %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________\n", this, listSize + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timing);
    }
}
