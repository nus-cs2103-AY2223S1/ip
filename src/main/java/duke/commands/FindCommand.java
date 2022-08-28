package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command for finding Tasks.
 */
public class FindCommand extends Command {
    private String remainingCommand;

    /**
     * FindCommand constructor.
     * @param remainingCommand
     */
    public FindCommand(String remainingCommand) {
        this.remainingCommand = remainingCommand;
    }

    /**
     * Executes a Find command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (remainingCommand.equals("")) {
            throw new DukeException("OOPS!!! Please enter a keyword for Find.");
        }
        String tasksFound = tasks.findTask(remainingCommand);
        ui.printFindTask(tasksFound);
    }

    /**
     * Checks if program should exit.
     *
     * @return false as program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
