package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

public class FindTaskCommand extends Command {

    private final int KEYWORD_START_INDEX = 5;
    private final String keywords;

    public FindTaskCommand(String input) {
        this.keywords = input.substring(KEYWORD_START_INDEX);
    }

    /**
     * Prints all the tasks in the task list that matches the keywords searched for by the user.
     *
     * @param tasks the TaskList to be search in
     * @param ui the ui to display messages
     * @param storage placeholder to match parameters defined in parent abstract class Command
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.findMessage();
        tasks.findTask(this.keywords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
