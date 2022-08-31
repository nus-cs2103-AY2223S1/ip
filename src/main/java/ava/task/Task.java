package ava.task;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

import ava.Ui;
import ava.processor.Storage;
import ava.processor.TaskList;

/**
 * Abstract class to represent the tasks.
 */
public abstract class Task {
    protected Boolean isBye;
    protected String description;
    protected Boolean isDone;

    /**
     * The constructor for task.
     *
     * @param description Task description.
     * @param isDone Task state.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isBye = false;
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
     * Returns the description of the current task.
     *
     * @return String description.
     */
    public String getDescription() {
        return this.description;
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
     * Changes the format of the output for listing tasklist.
     *
     * @param output The output from executing the commands.
     * @return A properly formatted string.
     */
    protected static String formatOutput(String prefix, String...output) {
        StringBuilder res = new StringBuilder(prefix).append("\n");
        for (String out : output) {
            res.append(out).append("\n");
        }
        return res.toString().trim();
    }

    /**
     * Executes process of the given task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     * @return The response of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Matches a keyword with the description of the Task.
     *
     * @param predicate The predicate as a string.
     * @return True if the description matches the keyword, false otherwise.
     */
    public final boolean matchKeyword(String predicate) {
        return Pattern.compile(".*\\b" + predicate + "\\b.*").matcher(description).find();
    }

    /**
     * Tests if a command is exit.
     *
     * @return True if isBye
     */
    public boolean isBye() {
        return isBye;
    }
}
