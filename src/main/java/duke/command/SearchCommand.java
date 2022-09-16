package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;

import java.time.LocalDateTime;

public class SearchCommand extends Command {
    private String keyword;

    public SearchCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.getSearchResults(this.keyword);
    }

}
