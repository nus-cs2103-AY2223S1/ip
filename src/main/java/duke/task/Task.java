package duke.task;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Represents a task.
 *
 * @author njxue
 * @version v0.1
 */
public class Task {
    /** Description of the task. **/
    private String description;
    /** Describes if the task is completed or not */
    private boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a Task object from a string.
     *
     * @param fileFormatString String representation of the Task object, in file format.
     * @return New Task object.
     */
    public static Task parse(String fileFormatString) {
        String[] taskSplit = fileFormatString.split("\\|");
        String taskSymbol = taskSplit[0];
        boolean isComplete = taskSplit[1].equals("1");
        String taskDescription = taskSplit[2];
        Task task;
        if (taskSymbol.equals("T")) {
            task = new Todo(taskDescription);
        } else if (taskSymbol.equals("D")) {
            LocalDateTime byDateTime = Parser.parseDateTime(taskSplit[3]);
            task = new Deadline(taskDescription, byDateTime);
        } else {
            LocalDateTime atDateTime = Parser.parseDateTime(taskSplit[3]);
            task = new Event(taskDescription, atDateTime);
        }
        if (isComplete) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns the status icon, which represents whether the task is completed.
     *
     * @return 'X' if the task is completed, returns a whitespace otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the test as completed.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the formatted task, which is to be written into the storage file.
     *
     * @return Formatted task, which is to be written into the storage file.
     */
    public String toFileFormatString() {
        int i = isDone ? 1 : 0;
        return "|" + i + "|";
    }
}
