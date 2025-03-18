package Duke;

/**
 * Duke.FixedDurationTask for tasks with duration but not date.
 */
public class FixedDurationTask extends Task {

    /**
     * String representation of the duration.
     */
    String duration;

    /**
     * Constructor for FixedDurationTask.
     *
     * @param name      String name of class
     * @param init      Boolean initialize
     * @param completed Boolean state of the task
     * @param duration  String duration of the task
     * @param listSize  int Size of the current list
     */
    public FixedDurationTask(String name, boolean init, boolean completed, String duration, int listSize) {
        super(name, completed);
        this.duration = duration;
        if (!init) {
            addMessage(listSize);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskType() {
        return "F";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addMessage(int listSize) {
        return String.format("     Got it. I've added this task:\n" +
                "     %s\n" +
                "     Now you have %d tasks in the list.\n", this, listSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + String.format(" (for: %s)", duration);
    }
}