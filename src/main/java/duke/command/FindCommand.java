package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Class which handles finding tasks.
 */
public class FindCommand extends Command {

    private String keyword;
    private StringBuilder output;

    /**
     * Creates an instance of find command.
     * @param keyword word to search for in tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        output = new StringBuilder("Here are the matching tasks in your list:");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        int len = tasks.listSize();
        int counter = 1;

        for (int i = 1; i <= len; i++) {
            if (tasks.showTask(i).contains(keyword)) {
                output.append("\n").append(counter).append(". ").append(tasks.showTask(i));
                counter += 1;
            }
        }
        return output.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
