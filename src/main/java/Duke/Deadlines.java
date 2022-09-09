package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Duke.Task with an associated deadline.
 */
public class Deadlines extends Task {

    /**
     * deadline of the task
     */
    private final LocalDateTime deadline;

    /**
     * Constructor to initialize deadline task.
     *
     * @param name      name of task
     * @param init      true if initialized from data file
     * @param completed true if task is completed
     * @param deadline  time of deadline
     * @param listSize  current size of list containing all tasks
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
        String format = "EEEE, MMM dd, yyyy HH:mm ";
        return "[D]" + super.toString() + String.format(" (by: %s)",
                deadline.format(DateTimeFormatter.ofPattern(format)));
    }
}
