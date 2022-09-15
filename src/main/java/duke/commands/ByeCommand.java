package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/*
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    public ByeCommand(String instruction) {
        super(instruction);
    }

    /*
     * Executes the command.
     *
     * @param tasks The task list.
     * 
     * @param ui The user interface.
     * 
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExitMessage();
    }

    /*
     * Returns true if the command is an exit command.
     * ByeCommand is an exit command, hence returns true.
     *
     * @return true if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
