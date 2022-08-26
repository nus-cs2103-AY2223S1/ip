package duke.command;

import duke.exception.DukeIndexOutOfBoundException;
import duke.exception.DukeIoException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkUndoneCommand extends Command {
    int taskIndex;

    MarkUndoneCommand(int taskIndex) {
        super(CommandType.MARK_UNDONE);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = taskList.markTaskUndone(taskIndex);
        } catch (DukeIndexOutOfBoundException exception) {
            output = exception.getMessage();
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
