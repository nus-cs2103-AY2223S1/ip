/**
 * Task with an associated timing.
 */
public class Events extends Task {

    /**
     * timing of event.
     */
    String timing;

    /**
     * Constructor to initialize class.
     *
     * @param name  task name
     */
    public Events(String name, String timing) {
        super(name);
        this.timing = timing;
        addMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMessage() {
        System.out.printf(
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "     %s\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n", this, Duke.taskArray.size() + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", timing);
    }
}
