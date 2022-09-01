package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        return Command.wrapper.getFindResponse(tasklist.getTaskListWithKeyword(keyword).toString());
    }
}
