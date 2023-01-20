package bob.commands;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * ListCommand class to handle "list" keyword
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     */
    public ListCommand() {
        super();
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage, Ui ui) {
        return ui.displayTaskList(taskList, "here are your tasks!");
    }
}
