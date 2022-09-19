package duke.command;

import duke.Storage;
import duke.TaskList;

public class SortCommand extends Command {

    private String sortType;
    private boolean isDescending;

    public SortCommand(String sortType, boolean isDescending) {
        this.sortType = sortType;
        this.isDescending = isDescending;
    }

    public String execute(TaskList tasks, Storage storage) {
        tasks.sort(sortType, isDescending);
        return "I've sorted the list!";
    }
}
