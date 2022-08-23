package duke;

/**
 * task with only name
 */
public class Todo extends Task {
    private static final String type = "[T]";

    /**
     * constructor for new todo instance
     * @param name name of task
     * @throws MissingDescriptionException missing name of task
     */
    public Todo(String name) throws MissingDescriptionException {
        super(name);
    }

    /**
     * returns string representation of task.
     * @return string with type, completed or not, name, and date time(if applicable)
     */
    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return type + comp + name;
    }

    /**
     * return string representation of task to be written in text file
     * @return string representation to be written in text file
     */
    @Override
    public String toData() {
        String type = "T";
        String completed = this.completed ? "1" : "0";
        return type + "//" + completed +"//" + name;
    }
}
