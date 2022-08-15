/**
 * The Task class represents a task added to Duke.
 */
public class Task {
    private static int total = 0;
    private String name;
    private int id;

    /**
     * Initialises a Task with a name.
     *
     * @param name A string representing the name of the Task.
     */
    public Task(String name) {
        this.name = name;
        id = ++total;
    }

    public static int getTotal() {
        return total;
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return The string representing the Task.
     */
    @Override
    public String toString() {
        return id + ". " + name;
    }
}
