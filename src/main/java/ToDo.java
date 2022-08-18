/**
 * Simple ToDo Task.
 *
 */
public class ToDo extends Task {

    /**
     * Constructor to initialize class.
     *
     * @param name task name
     */
    public ToDo(String name) {
        super(name);
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
        return "[T]" + super.toString();
    }
}
