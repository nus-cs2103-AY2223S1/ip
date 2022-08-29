package seedu.duke.command;

import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showNoTask();
        }
        for (int i = 1; i <= tasks.numOfTasks(); i++) {
            System.out.println(String.format("%d. %s", i, tasks.fetchTask(i).toString()));
        }
    }
}
