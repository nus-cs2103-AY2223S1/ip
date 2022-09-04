package duke.task;

/**
 * Defines {@code ToDo} class.
 */
public class ToDo extends Task {
    /**
     * Constructor for {@code ToDo}.
     * @param description Description of task.
     * @param priority    Priority level of task.
     */
    public ToDo(String description, Level priority) {
        super(description, priority);
    }

    /**
     * Constructor for {@code ToDo} if {@code isDone} is known.
     * @param description Description of task.
     * @param isDone      Whether task is done.
     * @param priority    Priority level of task.
     */
    public ToDo(String description, Boolean isDone, Level priority) {
        super(description, isDone, priority);
    }

    /**
     * Overrides {@code toString} method to return status and
     * description of {@code ToDo} object.
     * @return [T][[COMPLETION STATUS]][[PRIORITY]] [TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Produces a {@code String} with "|" delimiters for
     * storing the task's data into a text file.
     * @return "todo|[COMPLETION STATUS]|[TASK DESCRIPTION]|[PRIORITY]"
     */
    public String toFileFormat() {
        return "todo" + "|" + super.toFileFormat();
    }
}
