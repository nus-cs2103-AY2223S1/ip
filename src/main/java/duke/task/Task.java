package duke.task;

/**
 * Defines <Code>Task</Code> class.
 */
public abstract class Task {
    /** Description of task. */
    protected String description;

    /** <Code>Boolean</Code> object representing whether task is done. */
    protected boolean isDone;

    /**
     * Constructor for <Code>Task</Code>.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for <Code>Task</Code> with <Code>isDone</Code> known.
     * @param description Description of task.
     * @param isDone      Whether task is done.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Shows whether task is done.
     * @return "X" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks <Code>Task</Code> as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks <Code>Task</Code> as undone.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    /**
     * Overrides <Code>toString</Code> method to return status
     * and description of <Code>Task</Code>.
     * @return [[TASK STATUS]] [TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Produces <Code>String</Code> with "|" delimiters for storing task's data
     * into a text file.
     * @return "deadline|[COMPLETION STATUS]|[TASK DESCRIPTION]|[TASK DEADLINE]
     */
    public String toFileFormat() {
        return this.isDone + "|" + this.description;
    }

    /**
     * Returns whether <Code>Task</Code>'s description contains given word.
     * @param word Word to search description for.
     * @return     <Code>Boolean</Code> value of whether <Code>Task</Code>'s
     *             description contains given word.
     */
    public Boolean hasWord(String word) {
        return this.description.contains(word);
    }
}
