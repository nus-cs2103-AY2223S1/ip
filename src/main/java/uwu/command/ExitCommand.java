package uwu.command;

import uwu.uwu.Storage;

import uwu.task.TaskList;

import uwu.uwu.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand which is to exit the program.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     */
    public String execute (TaskList tasks, Ui ui, Storage storage) {
        return ui.leaveChat();
    }

    /**
     * Returns whether ExitCommand exits the program.
     *
     * @return true as ExitCommand exits the program.
     */
    public boolean isExit() {
        return true;
    }
}
