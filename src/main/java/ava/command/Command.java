package ava.command;

import ava.Ui;
import ava.util.Storage;
import ava.util.TaskList;

/**
 * Abstract class to represent the commands.
 */
public abstract class Command {
    /**
     * Abstract method to test if a command is exit.
     *
     * @return True if isBye
     */
    public abstract boolean isBye();

    /**
     * Abstract method to execute process of the given task.
     *
     * @param tasks TaskList.
     * @param ui Class to print the ui.
     * @param storage Class to write/read commands from file.
     * @return The response of the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);



    /**
     * Changes the format of the output for listing tasklist.
     *
     * @param output The output from executing the commands.
     * @return A properly formatted string.
     */
    public static String formatOutput(String prefix, String... output) {
        StringBuilder res = new StringBuilder(prefix).append("\n");
        for (String out : output) {
            res.append(out).append("\n");
        }
        return res.toString().trim();
    }
}
