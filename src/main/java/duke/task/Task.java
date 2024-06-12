package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class that represents a general task.
 */
public abstract class Task {

    protected static final String OUTPUT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_FORMAT);
    private static final String FILE_WRITING_DELIMITER = "|";

    protected String taskTitle;
    protected boolean isDone;
    protected TaskType taskType;

    protected Task(String taskTitle, TaskType taskType) {
        this(taskTitle, false, taskType);
    }

    protected Task(String taskTitle, boolean done, TaskType taskType) {
        this.taskTitle = taskTitle;
        this.isDone = done;
        this.taskType = taskType;
    }

    /**
     * Changes the internal state to be done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Changes the internal state to be undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * A LocalDateTime getter. By default, it is the max value.
     *
     * @return The date of the task.
     */
    public LocalDateTime getDate() {
        return LocalDateTime.MAX;
    }

    /**
     * Returns the string representation of a task that gives the information about the internal state of this task.
     *
     * @param label A capital letter that indicates the type of the task.
     * @param displayedText Other text to append.
     * @return The string representation of a task
     */
    protected String getStringRepresentation(String label, String displayedText) {
        return "[" + (isDone ? "X" : " ") + "] " + "[" + label + "] " + displayedText;
    }

    /**
     * Returns a string representation for file saving.
     *
     * @return A string that is suitable to be saved as plain text.
     */
    public abstract String getFileRepresentation();

    /**
     * Returns a string representation of the task in the file.
     *
     * @param label A capital letter that indicates the type of the task.
     * @return A string representation of the task in the file.
     */
    protected String getFileRepresentation(String label, String dateTime) {
        String output = label + " " + FILE_WRITING_DELIMITER + " "
                + (isDone ? "1" : "0")
                + " " + FILE_WRITING_DELIMITER + " " + taskTitle;
        if (dateTime != null) {
            output += " " + FILE_WRITING_DELIMITER + " " + dateTime;
        }
        return output;
    }

    /**
     * Overloads getFileRepresentation()
     *
     * @param label A capital letter that indicates the type of the task.
     * @return A string representation of the task in the file.
     */
    protected String getFileRepresentation(String label) {
        return getFileRepresentation(label, null);
    }

    /**
     * Indicates whether the task title contains a search keyword.
     *
     * @param keyword The keyword being looked up.
     * @return True if the task title contains that keyword.
     */
    public boolean contains(String keyword) {
        return taskTitle.contains(keyword);
    }
}
