package duke;

/**
 * Represents the Todo task which is a type of task
 * It has the tag [T]
 */
public class Todo extends Task {
    private final String tag = "[T]";

    /**
     * Constructor for Todo Class
     *
     * @param name Name of the task
     */
    public Todo(String name) {
        super(name);
    }


    /**
     * Returns a string representation of the class
     *
     * @return String Representation of the class
     */
    @Override
    public String toString() {
        return tag + "[" + this.getStatusIcon() + "] " + this.getTaskName() + " " + this.getTagString();
    }
}
