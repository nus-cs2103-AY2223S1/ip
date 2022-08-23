package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class MarkTaskNotDoneCommand extends Command {
    private int taskNumber;

    public MarkTaskNotDoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        Ui.prettyPrint(tasks.markTaskNotDone(this.taskNumber));
    }
}
