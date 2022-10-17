package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a class to search for tasks in the tasklist using keyword
 */
public class FindCommand extends  Command {

    protected String search;
    private static final String MESSAGE = "Here are the mathces from " +
            "your list:\n";
    private final String NO_MATCH = "You currently have no tasks matching that " +
            "description";

    /**
     * Constructs a find command.
     * @param search the keyword to search for.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList searchMatchList = new TaskList(taskList.findTasks(search));
        if (searchMatchList.getSize() == 0) {
            return ui.displayMessage(NO_MATCH);
        }
        return ui.displayMessage(MESSAGE + searchMatchList.taskListString());
    }



}