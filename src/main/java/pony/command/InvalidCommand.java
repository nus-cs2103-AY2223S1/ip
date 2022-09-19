package pony.command;

import pony.Storage;
import pony.TaskList;
import pony.Ui;

/**
 * Command for an Invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Executes an Invalid command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     * @return A command message.
     */
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String message = ":( OOPS!!! I'm sorry, but I don't know what that means...";
        return message;
    }
}
