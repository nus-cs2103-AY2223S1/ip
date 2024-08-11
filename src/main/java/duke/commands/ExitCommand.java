package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Represents a command that stops the programme.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_SUCCESS = "Never wanna see you again!";

    /**
     * Execute the command by ending the programme.
     *
     * @param tasks Task List that stores tasks.
     * @param storage Storage in charge of writing to the local disk.
     * @return A string showing a message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return MESSAGE_SUCCESS;
    }

}
