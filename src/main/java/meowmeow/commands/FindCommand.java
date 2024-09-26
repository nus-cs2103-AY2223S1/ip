package meowmeow.commands;

import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class FindCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used when the user enters the "find" command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class FindCommand extends Command {

    private String userInput;

    /**
     * Constructor for FindCommand.
     *
     * @param userInput the user input to be used by the command.
     *                  The user input is used to find the task in the task list.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the FindCommand.
     * Finds the task in the task list that matches the user input.
     *
     * @param tasks the task list to be operated on by the command.
     *              The task list is used to find the task in the task list.
     * @param ui the user interface to be used by the command.
     *           The user interface is used to display the result of the command.
     * @param storage the storage to be used by the command.
     *                The storage is used to save and load the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.findTask(userInput);
    }

    /**
     * Returns false for FindCommand.
     *
     * @return false for FindCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
