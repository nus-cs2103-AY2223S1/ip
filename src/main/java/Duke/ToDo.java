package Duke;

/**
 * Simple Duke.ToDo Duke.Task.
 */
public class ToDo extends Task {

    /**
     * Constructor to initialize class.
     *
     * @param name task name
     */
    public ToDo(String name, boolean init, boolean completed, int listSize) {
        super(name, completed);
        if (!init) {
            addMessage(listSize);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskType() {
        return "T";
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
        return "[T]" + super.toString();
    }
}
