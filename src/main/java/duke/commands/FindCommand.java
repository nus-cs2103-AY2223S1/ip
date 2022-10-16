package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends  Command {

    protected String search;
    private static final String MESSAGE = "Here are the mathces from " +
            "your list:\n";
    private final String NO_MATCH = "You currently have no tasks matching that " +
            "description";

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