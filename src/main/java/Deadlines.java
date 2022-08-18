/**
 * Task with an associated deadline
 */
public class Deadlines extends Task {

    /**
     * deadline of the task
     */
    String deadline;

    /**
     * Constructor to initialize class.
     *
     * @param name  task name
     */
    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
        addMessage();
    }

    @Override
    public void addMessage() {
        System.out.printf(
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "     %s\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n", this, Duke.taskArray.size() + 1);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", deadline);
    }
}
