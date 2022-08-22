package duke.duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Command to list all Tasks in the TaskList.
 * Inherits from Command abstract class.
 */
public class ListCommand extends Command {

    /**
     * Executes the command given.
     * All the Tasks in TaskList will be printed out to user.
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
    }
}
