package duke;

/**
 * Public Task class that represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String code;

    /**
     * Constructs an unmarked Task object based on its description and code.
     * <ul>
     *     <li>Code "T" represents ToDo task</li>
     *     <li>Code "E" represents Event task</li>
     *     <li>Code "D" represents Deadline task</li>
     * </ul>
     * @param description Description of the task
     * @param code Code of the task
     */
    public Task(String description, String code) {
        this.description = description;
        this.code = code;
        setIsDone(false);
    }

    /**
     * Constructs an Task object based on its description and code.
     * <ul>
     *     <li>Code "T" represents ToDo task</li>
     *     <li>Code "E" represents Event task</li>
     *     <li>Code "D" represents Deadline task</li>
     * </ul>
     * @param description Description of the task
     * @param code Code of the task
     * @param isDone Status of the task
     */
    public Task(String description, String code, boolean isDone) {
        this.description = description;
        this.code = code;
        setIsDone(isDone);
    }

    /**
     * Obtains status of the task in form of string.
     * <ul>
     *     <li>"X" indicates done</li>
     *     <li>" " indicates undone</li>
     * </ul>
     * @return Status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Obtains description of the Task.
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Obtains code of task.
     * <ul>
     *      <li>Code "T" represents ToDo task</li>
     *      <li>Code "E" represents Event task</li>
     *      <li>Code "D" represents Deadline task</li>
     * </ul>
     * @return Code of task
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Modifies status of the task.
     * @param value Status of task
     */
    public void setIsDone(boolean value) {
        this.isDone = value;
    }

    /**
     * Marks Task as done.
     */
    public void markAsDone() {
        setIsDone(true);
    }

    /**
     * Unmarks Task as undone.
     */
    public void unmarkAsDone() {
        setIsDone(false);
    }

    /**
     * Prints text format of the Task.
     * For example:
     * "E | X | CS2103 Meeting"
     * @return Text format of task
     */
    public String printText() {
        return this.getCode() + " | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    /**
     * Represents the Task object as a string.
     * @return String representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}