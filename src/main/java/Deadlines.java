/**
 * Task with an associated deadline
 */
public class Deadlines extends Task {

    /**
     * deadline of the task
     */
    private String deadline;

    /**
     * Constructor to initialize class.
     *
     * @param name task name
     */
    public Deadlines(String name, boolean init, boolean completed, String deadline) {
        super(name, init, completed);
        this.deadline = deadline;
        if (!init) {
            addMessage();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskType() {
        return "D";
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
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }
}
