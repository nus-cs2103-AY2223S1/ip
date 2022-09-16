package duke.commands;

import java.util.List;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * This class represents a command to find tasks that contains all
 * the keywords provided
 */
public class FindCommand extends Command {
    private final String[] keyword;

    /**
     * Constructs a new Find Command
     * @param keyword A list of keywords to match
     */
    public FindCommand(String ...keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        List<Task> list = taskList.find(keyword);
        return ui.printFind(list);
    }
}
