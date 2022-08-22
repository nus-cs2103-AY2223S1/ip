package duke.task;

public abstract class Task {
    /**
     * Description of task.
     */
    protected String description;

    /**
     * Boolean object to determine if task is done or not.
     */
    protected boolean isDone;

    /**
     * Constructor method for `Task`.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor method for `Task`.
     * @param description Description of task.
     * @param isDone      Whether task is done.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Show if task is done.
     * @return "X" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Setter method to mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Setter method to mark task as undone.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    /**
     * Override 'toString' method to return status and description of Task.
     * @return [[TASK STATUS]] [TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.getStatusIcon(), this.description);
    }

    /**
     * To produce a String with "|" delimiters for storing the task's data
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
