package duke;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a Task.
 * A <code>Task</code> object has
 * description and done status
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a task with a description and
     * is undone.
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of task status.
     *
     * @return string representation of task status.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Change the status of the task.
     */
    public void toggleStatus() {
        this.isDone = !isDone;
    }

    /**
     * Returns boolean representation of task status.
     *
     * @return boolean.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Compares if two task are the same.
     *
     * @param obj Object of comparison.
     * @return string representation of task status.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task x = (Task) obj;
            if (this.description == null || x.description == null) {
                return false;
            }
            return this.description.equals(x.description);
        }

        return false;
    }

    /**
     * Returns true if description matches keyword.
     *
     * @param keyword target keyword.
     * @return boolean.
     */
    public boolean isMatchKeyword(String keyword) {
        boolean result = false;
        Stream<String> matched = Arrays.stream(
                this.description.split(" "));
        List<String> filtered = matched
                .filter(str -> str.equals(keyword))
                .collect(Collectors.toList());
        return filtered.size() > 0;
    }

    /**
     * Returns compact string representation of task.
     *
     * @return string representation.
     */
    public abstract String toSaveVersion();
}
