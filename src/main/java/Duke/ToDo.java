package Duke;

/**
 * Simple Duke.ToDo Duke.Task.
 */
public class ToDo extends Task {

    /**
     * Constructor for class
     *
     * @param name      name of task
     * @param init      true if initialized from data file
     * @param completed true if task is completed
     * @param listSize  current size of list containing all tasks
     */
    public ToDo(String name, boolean init, boolean completed, int listSize) {
        super(name, completed);
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
    public String addMessage(int listSize) {
        return String.format("     Got it. I've added this task:\n" +
                        "     %s\n" +
                        "     Now you have %d tasks in the list.\n", this, listSize + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
