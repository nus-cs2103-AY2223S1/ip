package duke.task;

/**
 * Defines {@code Task}  class.
 */
public abstract class Task {
    /** Description of task. */
    protected String description;

    /** {@code Boolean}  object representing whether task is done. */
    protected boolean isDone;

    /** {@code int}  attribute representing task's priority. */
    protected Level priority;

    /**
     * Constructor for {@code Task}.
     * {@code isDone} defaults to {@code false} and {@code priority}
     * defaults to {@code LOW}.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Level.LOW;
    }

    /**
     * Constructor for {@code Task} with only {@code isDone} known.
     * {@code priority} defaults to {@code LOW}.
     * @param description Description of task.
     * @param priority    Priority level of task.
     */
    public Task(String description, Level priority) {
        this.description = description;
        this.priority = priority;
    }

    /**
     * Constructor for {@code Task} with both {@code isDone} and
     * {@code priority} known.
     * @param description Description of task.
     * @param isDone      Whether task is done.
     * @param priority    Priority level of task.
     */
    public Task(String description, Boolean isDone, Level priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Shows whether task is done.
     * @return "X" if task is done, " " if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks {@code Task}  as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks {@code Task}  as undone.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    /**
     * Overrides {@code toString}  method to return status
     * and description of {@code Task} .
     * @return [[COMPLETION STATUS]][[PRIORITY]] [TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.getStatusIcon(), this.priority, this.description);
    }

    /**
     * Produces {@code String}  with "|" delimiters for storing
     * task's data into a text file.
     * @return [COMPLETION STATUS]|[TASK DESCRIPTION]|[PRIORITY]
     */
    public String toFileFormat() {
        return this.isDone + "|" + this.description + "|" + this.priority;
    }

    /**
     * Returns whether {@code Task} 's description contains given word.
     * @param word Word to search description for.
     * @return     {@code Boolean}  value of whether {@code Task} 's
     *             description contains given word.
     */
    public Boolean hasWord(String word) {
        return this.description.contains(word);
    }
}
