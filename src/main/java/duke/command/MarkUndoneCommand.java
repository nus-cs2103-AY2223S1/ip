package duke.command;

import duke.Duke;
import duke.exception.DukeIndexOutOfBoundException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkUndoneCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Sure, I have marked this as not done yet.\n" + Duke.TAB;
    private static final String ERROR_MESSAGE =
            "Oops! Do check the index range, and the format should be \"unmark <index>\"";

    int taskIndex;

    MarkUndoneCommand(int taskIndex) {
        super(CommandType.MARK_UNDONE);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = OUTPUT_MESSAGE + taskList.markTaskUndone(taskIndex);
        } catch (DukeIndexOutOfBoundException exception) {
            output = ERROR_MESSAGE;
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
