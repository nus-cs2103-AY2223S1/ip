package duke.command;

import duke.exception.IllegalKeywordException;
import duke.logic.TaskList;

public class FindCommand extends Command {
    private TaskList taskList;
    private String keyword;

    public FindCommand(TaskList taskList, String keyword) throws IllegalKeywordException {
        this.taskList = taskList;
        //double check only
        if (keyword.length() > 0) {
            this.keyword = keyword;
        } else {
            throw new IllegalKeywordException("No keyword.");
        }
    }

    @Override
    public String get() {
        return "Here are the matching tasks in your list:\n"
                + this.taskList.search(this.keyword).toString();
    }
}
