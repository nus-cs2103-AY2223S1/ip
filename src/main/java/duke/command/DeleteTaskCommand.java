package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class DeleteTaskCommand extends Command {
    private int taskNumber;

    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        // TODO: prettyPrint(tasks.deleteTask(Integer.parseInt(cmd.substring(7))));
        Ui.prettyPrint(tasks.deleteTask(this.taskNumber));
    }
}
