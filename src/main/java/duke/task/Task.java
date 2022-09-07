package duke.task;

import java.time.LocalDateTime;

import duke.exception.FileCorruptedException;
import duke.util.Parser;

/**
 * Represents a task.
 *
 * @author njxue
 * @version v0.1
 */
public abstract class Task {
    /** Icon to represent that a task is completed. */
    private static final String COMPLETED_ICON = "X";
    /** Icon to represent that a task is not completed. */
    private static final String INCOMPLETE_ICON = " ";

    /** Description of the task. */
    private String description;
    /** Describes if the task is completed or not. */
    private boolean isDone;
    /** Date and time the task occurs or to be completed by. */
    private LocalDateTime dateTime;
    /** Date and time the task was added to the TaskList. */
    private LocalDateTime dateAdded = LocalDateTime.now();

    /**
     * Creates a Task object not associated with a date and time.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        dateTime = LocalDateTime.MAX;
    }

    /**
     * Creates a Task object associated with a date and time.
     *
     * @param description Description of the task.
     * @param dateTime Date and time of the task.
     */
    public Task(String description, LocalDateTime dateTime) {
        this.description = description;
        this.isDone = false;
        this.dateTime = dateTime;
    }

    /**
     * Returns a Task object from a string.
     *
     * @param fileFormatString String representation of the Task object, in file format.
     * @return New Task object.
     */
    public static Task parse(String fileFormatString) throws FileCorruptedException {
        String[] taskSplit = fileFormatString.split("\\|");

        assert(taskSplit.length >= 3);

        String taskSymbol = taskSplit[0];
        boolean isComplete = taskSplit[1].equals(COMPLETED_ICON);
        String taskDescription = taskSplit[2];
        Task task;

        if (taskSymbol.equals("T")) {
            task = new Todo(taskDescription);
        } else if (taskSymbol.equals("D")) {
            assert(taskSplit.length == 4);
            LocalDateTime byDateTime = Parser.parseDateTime(taskSplit[3]);
            task = new Deadline(taskDescription, byDateTime);
        } else if (taskSymbol.equals("E")) {
            assert(taskSplit.length == 4);
            LocalDateTime atDateTime = Parser.parseDateTime(taskSplit[3]);
            task = new Event(taskDescription, atDateTime);
        } else {
            throw new FileCorruptedException("Unable to read [" + taskSymbol + "] from file.");
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
        return isDone ? COMPLETED_ICON : INCOMPLETE_ICON;
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
     * Returns the deadline or time of the task.
     *
     * @return Deadline or time of the task.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the date and time the task was added.
     *
     * @return Date and time the task was added.
     */
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    /**
     * Returns the formatted task, which is to be written into the storage file.
     *
     * @return Formatted task, which is to be written into the storage file.
     */
    public String toFileFormatString() {
        String icon = getStatusIcon();
        return "|" + icon + "|";
    }
}
