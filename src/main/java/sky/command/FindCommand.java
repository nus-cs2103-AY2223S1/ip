package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.Ui;
import sky.exception.TextNoMeaningException;

/**
 * The FindCommand class deals with finding tasks that contains a keyword.
 */
public class FindCommand extends Command {
    private String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws TextNoMeaningException {
        try {
            String keyword = this.fullCommand.substring(5);
            String s = taskList.findTasksThatContains(keyword);
            if (!s.equals("")) {
                ui.displayText(s);
            }
            return s;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  You have not entered a keyword.");
        }
        throw new TextNoMeaningException("  Error executing FindCommand.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
