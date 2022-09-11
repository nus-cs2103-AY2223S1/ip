package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List Command class.
 */
public class ListCommand extends Command {

    /**
     * Prints all the tasks in tasks.
     *
     * @param tasks The tasks to be executed.
     */
    @Override
    public String execute(TaskList tasks) {
        int i = 0;
        String response = "";
        for (Task task : tasks.getTasks()) {
            i = i + 1;
            response = response + i + ". " + task + "\n";
        }

        return Ui.showPrintListMessage() + response;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
