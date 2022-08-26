package duke.command;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddDeadlineCommand extends Command {

    DeadlineTask task;

    AddDeadlineCommand(DeadlineTask task) {
        super(CommandType.ADD_DEADLINE);
    }

    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.addNewTask(task);
        } catch (DukeException exception) {
            output = exception.getMessage();
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
