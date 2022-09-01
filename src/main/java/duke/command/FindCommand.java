package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that finds tasks in the list matching the input String.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class FindCommand extends Command {

    private String target;

    /**
     * Instantiates command with the keyword to be matched.
     *
     * @param target Keyword to be matched against.
     */
    public FindCommand(String target) {
        this.target = target;
    }

    /**
     * Returns whether command is an ExitCommand.
     *
     * @return Whether the command will cause the Duke program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
    /**
     * Executes the command by finding tasks containing the target keyword.
     *
     * @param taskList List of tasks being operated on.
     * @param ui UI that prints corresponding responses.
     * @param storage Storage for saving purposes if applicable.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showFound() + taskList.find(target);
    }
}
