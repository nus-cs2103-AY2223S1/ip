package functional;

import java.time.LocalDateTime;

import technical.SaveLine;

/**
 * Class for tasks.
 * @author Nicholas Patrick
 */
public abstract class Task {
    private static final String TASK_INFOTYPE = "task";
    private static final String TASK_NAME_LABEL = "name";
    private static final String TASK_IS_DONE_LABEL = "done";
    protected String name;
    protected boolean isDone;

    /**
     * Construct functional.Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Construct a task from a SaveLine.
     *
     * @param line The SaveLine containing necessary information.
     */
    public Task(SaveLine line) {
        name = line.getValue(TASK_NAME_LABEL);
        isDone = line.getValue(TASK_IS_DONE_LABEL).equals("1");
    }

    /**
     * Get the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Get whether the task is done or not.
     *
     * @return Whether the task is done or not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark a task as done.
     */
    public void doTask() {
        isDone = true;
    }

    /**
     * Mark a task as not done.
     */

    public void undo() {
        isDone = false;
    }

    /**
     * Get the character symbol of the done-ness of the task.
     *
     * @return Returns 'X' for done tasks and ' ' for not done tasks.
     */

    public char mark() {
        return isDone ? 'X' : ' ';
    }

    /**
     * Shows the task name and status as a String.
     *
     * @return A String with the task name and status.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", mark(), name);
    }

    /**
     * Turns the task into a SaveLine, so it's ready to be saved. Can also be
     * used to compare two tasks.
     *
     * @return A SaveLine with the data associated with the task.
     */
    public SaveLine toData() {
        return new SaveLine(TASK_INFOTYPE, TASK_NAME_LABEL, name, TASK_IS_DONE_LABEL, isDone ? "1" : "0");
    }

    public abstract LocalDateTime getTime();
}
