package duke.command;

import duke.Duke;
import duke.exception.DukeIndexOutOfBoundException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkDoneCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Good to hear that! I have marked this as done: \n" + Duke.TAB;
    private static final String ERROR_MESSAGE =
            "Oops! Do check the index range, and the format should be \"mark <index>\"";

    int taskIndex;

    public MarkDoneCommand(int taskIndex) {
        super(CommandType.MARK_DONE);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            output = OUTPUT_MESSAGE + taskList.markTaskDone(taskIndex);
        } catch (DukeIndexOutOfBoundException exception) {
            output = ERROR_MESSAGE;
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
