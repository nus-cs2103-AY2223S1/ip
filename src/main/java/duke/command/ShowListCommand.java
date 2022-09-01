package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that prints out all the tasks in the list.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class ShowListCommand extends Command {

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
     * Executes the command by printing the list of tasks.
     *
     * @param taskList List of tasks being operated on.
     * @param ui UI that prints corresponding responses.
     * @param storage Storage for saving purposes if applicable.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printList();
    }

}
