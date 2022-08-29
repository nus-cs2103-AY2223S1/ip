package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {}

    /**
     * Method that executes the mark command to mark the specified task done or not done.
     *
     * @param tasks Task list containing the task to be marked.
     * @param ui Ui that will output messages to the user.
     * @param storage Storage on hard disk that stores the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.farewell();
        System.exit(0);
    }
}
