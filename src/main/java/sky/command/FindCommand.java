package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;

/**
 * The FindCommand class deals with finding tasks that contains a keyword.
 */
public class FindCommand extends Command {
    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList taskList, Storage storage) throws TextNoMeaningException {
        try {
            String keyword = this.fullCommand.substring(5);
            String s = taskList.findTasksThatContains(keyword);
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have not entered a keyword.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
