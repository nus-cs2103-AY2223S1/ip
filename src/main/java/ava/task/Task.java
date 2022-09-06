package ava.task;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

/**
 * Abstract class to represent the tasks.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected Boolean isDone;

    /**
     * The constructor for task.
     *
     * @param description Task description.
     * @param isDone Task state.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the current status of task.
     *
     * @return String status.
     */
    public String getStatus() {
        byte[] emojiCheckByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85};
        String check = new String(emojiCheckByteCode, Charset.forName("UTF-8"));
        byte[] emojiCrossByteCode = new byte[]{(byte) 0xE2, (byte) 0x9D, (byte) 0x8C};
        String cross = new String(emojiCrossByteCode, Charset.forName("UTF-8"));
        return (this.isDone ? check : cross);
    }

    /**
     * Marks done a task.
     *
     * @return Task object.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks undone a task.
     *
     * @return Task object.
     */
    public Task markUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Changes the format of Task to write to the file.
     *
     * @return String format to write to file.
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "|" + mark + "0" + this.description;
    }

    /**
     * Obtains a string representation of the task.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return this.getStatus() + " - " + this.description;
    }

    /**
     * Matches a keyword with the description of the Task.
     *
     * @param predicate The predicate as a string.
     * @return True if the description matches the keyword, false otherwise.
     */
    public final boolean matchKeyword(String predicate) {
        return Pattern.compile(".*\\b" + predicate + "\\b.*").matcher(description).find();
    }
}
