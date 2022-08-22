package duke.task;

/**
 * Defines <Code>ToDo</Code> class.
 */
public class ToDo extends Task {
    /**
     * Constructor for <Code>ToDo</Code>.
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for <Code>ToDo</Code> if <Code>isDone</Code> is known.
     * @param description Description of task.
     * @param isDone      Whether task is done.
     */
    public ToDo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides <Code>toString</Code> method to return status and
     * description of <Code>ToDo</Code> object.
     * @return [T][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Produces a <Code>String</Code> with "|" delimiters for
     * storing the task's data into a text file.
     * @return "todo|[COMPLETION STATUS]|[TASK DESCRIPTION]|"
     */
    public String toFileFormat() {
        return "todo" + "|" + super.toFileFormat();
    }
}
