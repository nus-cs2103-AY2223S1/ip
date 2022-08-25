package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Duke.Task with an associated deadline
 */
public class Deadlines extends Task {

    /**
     * deadline of the task
     */
    private final LocalDateTime deadline;

    /**
     * Constructor to initialize class.
     *
     * @param name task name
     */
    public Deadlines(String name, boolean init, boolean completed, LocalDateTime deadline, int listSize) {
        super(name, completed);
        this.deadline = deadline;
        if (!init) {
            addMessage(listSize);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDate() {
        return deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
        String format = "EEEE, MMM dd, yyyy HH:mm ";
        return "[D]" + super.toString() + String.format(" (by: %s)",
                deadline.format(DateTimeFormatter.ofPattern(format)));
    }
}
