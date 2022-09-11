package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks matching a given pattern.
 *
 * @author Rama Aryasuta Pangestu
 */
public class FindCommand extends Command {
    private final String pattern;

    /**
     * Constructs a command to list all tasks whose description matches the specified pattern.
     *
     * @param pattern the specified pattern
     */
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Prints a task list where each tasks' description includes the specified pattern
     * as a substring, ignoring lower and upper case difference.
     *
     * @param ui       the user interface
     * @param storage  the storage dealing with loading and saving tasks in the save file
     * @param taskList the task list
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        TaskList filteredTaskList = taskList.filter(
                x -> x.getDescription().toLowerCase().contains(this.pattern.toLowerCase()));
        if (filteredTaskList.size() > 0) {
            ui.addOutput("Here are the matching tasks in your list:\n");
            ui.addOutput(filteredTaskList.toString());
        } else {
            ui.addOutput("No matching tasks :(\n");
        }
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
