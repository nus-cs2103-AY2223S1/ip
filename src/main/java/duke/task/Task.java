package duke.task;

import java.util.Objects;

/**
 * Generic Task with a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates task with a given description.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets string representation of the completion status.
     *
     * @return X if done else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets completion status of the task.
     *
     * @param isDone the new completion status.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the `Type` of the task.
     *
     * @return the type of the task.
     */
    public abstract Type getType();

    /**
     * Encodes the data to be saved to the file. Excludes completion status and task type.
     *
     * @return the encoded data.
     */
    public abstract String encodeData();

    /**
     * Returns the text to be displayed by the bot for the task, containing all details of the task,
     * excluding the type and completion status.
     *
     * @return the display text.
     */
    public abstract String getDisplayText();

    /**
     * Encodes the task type and completion status in addition to the output of encodeData() to be stored as
     * line in the saved file.
     *
     * @return the encoded data.
     */
    public final String encode() {
        return String.format("%s|%d|%s", this.getType().prefix, (this.isDone ? 1 : 0), this.encodeData());
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), getStatusIcon(), getDisplayText());
    }

    public boolean matches(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }

    /**
     * An enumeration of the different types of tasks.
     * Each task contains a unique prefix.
     */
    public enum Type {
        TODO("T"),
        DEADLINE("D"),
        EVENT("E");
        private final String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }

        /**
         * Returns the duke.task.Task Type given its prefix.
         *
         * @param prefix the prefix of the task type.
         * @return duke.task.Task.Type.
         * @throws IllegalArgumentException if none of the types match the prefix.
         */
        public static Type decode(String prefix) throws IllegalArgumentException {
            prefix = prefix.strip();
            for (Type type : Type.values()) {
                if (type.prefix.compareToIgnoreCase(prefix) == 0) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Task type not found.");
        }

        /**
         * Returns the prefix of the task type.
         *
         * @return String prefix of type.
         */
        @Override
        public String toString() {
            return prefix;
        }
    }
}
