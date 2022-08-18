/**
 * Task class to store the states whether task is complete or not
 *
 */
public class Task {
    /**
     * boolean state to store completed or not.
     */
    boolean completed = false;

    /**
     * name of task.
     */
    String taskName;

    /**
     * task ID.
     */
    int index;

    /**
     * Constructor to initialize class.
     *
     * @param name taskname
     * @param value task ID
     */
    public Task(String name, int value) {
        taskName = name;
        index = value;

        System.out.printf(
                "    ____________________________________________________________\n" +
                "     added: %s\n" +
                "    ____________________________________________________________\n", taskName);
    }

    /**
     * sets complete to true.
     *
     */
    public void mark() {
        completed = true;
        System.out.printf(
                "    ____________________________________________________________\n" +
                "     Nice! I've marked task %s as done:\n" +
                "     " + this.toString() + "\n" +
                "    ____________________________________________________________\n", index);
    }

    /**
     * sets complete to false.
     */
    public void unMark() {
        completed = false;
        System.out.printf(
                "    ____________________________________________________________\n" +
                "     Ok, I've marked task %s as not done yet:\n" +
                "     " + this.toString() + "\n" +
                "    ____________________________________________________________\n", index);
    }

    /**
     * Creates a String representation of Task.
     *
     */
    public String toString() {
        String marked = "[ ]";
        if (completed) {
            marked = "[X]";
        }
        return index + ". " + marked + " " + taskName;
    }
}
