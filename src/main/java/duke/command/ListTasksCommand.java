package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.prettyPrint(tasks.toString());
    }
}
