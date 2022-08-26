package duke.command;

import duke.exception.DukeIndexOutOfBoundException;
import duke.exception.DukeIoException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    int taskIndex;

    DeleteCommand(int taskIndex) {
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.deleteTask(taskIndex);
        } catch (DukeIndexOutOfBoundException exception) {
            output = exception.getMessage();
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
