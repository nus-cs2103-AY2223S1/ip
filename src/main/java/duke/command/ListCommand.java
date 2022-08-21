package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("===============================");
        tasks.listTasks();
        System.out.println("===============================");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
