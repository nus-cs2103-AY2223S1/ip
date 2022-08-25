package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class DoneCommand extends Command {
    private int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 1 || taskNum >= tasks.size()) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }

        tasks.markTaskAsDone(taskNum - 1);
        ui.printMessage("\tNice! I've marked this task as done:\n\t" +
                tasks.get(taskNum - 1).toString());
        storage.save(tasks.getAllTasks());
    }
}
