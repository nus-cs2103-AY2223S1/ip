package duke.task;

import duke.exception.DukeException;

/**
 * Class for Task.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Task {
    enum Priority {
        HIGH("High"), MEDIUM("Medium"), LOW("Low"), NA(" ");
        private String string;
        private Priority(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    /**
     * The details of the task.
     */
    protected String description;
    /**
     * The status of the task.
     */
    protected boolean isDone;
    /**
     * Priority level of task.
     */
    protected Priority priority;

    /**
     * A constructor to initialize Task.
     *
     * @param description The details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.NA;
    }

    /**
     * Get the string format of the status of the task.
     *
     * @return String format of the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Change the status of the task to completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Change the status of the task to uncompleted.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Change the priority of the task.
     *
     * @param stringPriority New priority of the task.
     */
    public void setPriority(String stringPriority) {
        String p = stringPriority.equals(" ") ? "NA" : stringPriority.toUpperCase();
        try {
            this.priority = Priority.valueOf(p);
        } catch (IllegalArgumentException e) {
            throw new DukeException(stringPriority + " is not a valid priority level.");
        }
    }

    /**
     * Get the string format of the task which will be written in the file.
     *
     * @return String format of the task which will be written in the file.
     */
    public String getStringToSave() {
        return "";
    }

    /**
     * Get string format of the task.
     *
     * @return Task in a string format.
     */
    @Override
    public String toString() {
        String s = String.format("[%s][%s] %s", this.getStatusIcon(), this.priority, this.description);
        return s;
    }
}
