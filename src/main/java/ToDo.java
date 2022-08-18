/**
 * The ToDo class extends the Task class as it is a more specific type of task.
 */
public class ToDo extends Task {

    /**
     * Public constructor of a ToDo class.
     *
     * @param name the name/description of the class.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Overrides the toString() method in the Task class, represents a ToDo by adding a "[T]" in front of the general
     * Task representation.
     *
     * @return String representation of a ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
