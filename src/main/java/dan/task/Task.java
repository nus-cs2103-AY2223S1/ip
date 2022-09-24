package dan.task;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method.
     * By default when a task is created, the status of completion of the task `isDone` is set to false.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets it's status icon based on the status of completion for the task.
     * 'X' for a completed task .
     * ' ' for a uncompleted task.
     *
     * @return The status icon
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Changes the completion status of the task.
     *
     * @param done The status of completion of the task
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Checks the task description contains a substring of the keywords provided.
     *
     * @param keywords that are to be searched
     * @return boolean if the task has the keyword in its description
     */
    public boolean hasKeyword(String... keywords) {
        return Arrays.stream(keywords).map(Pattern::compile)
                .map(p -> p.matcher(description))
                .anyMatch(Matcher::find);
    }

    /**
     * Generates the data string used for saving its data in the storage file.
     *
     * @param separator Regular expression to help the parsing of data
     * @return A String of data representing itself
     */
    public String toDataString(String separator) {
        return String.format("%s%d%s%s", separator, (this.isDone) ? 1 : 0, separator, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }


}
