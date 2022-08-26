package duke.command;

import duke.Duke;
import duke.exception.DukeIndexOutOfBoundException;
import duke.exception.DukeIoException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import static duke.Duke.TAB;

public class DeleteCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Sure, I have removed this task from the list: \n" + TAB;
    private static final String ERROR_MESSAGE =
            "Oops! Do check the index range, and the format should be \"delete <index>\"";

    int taskIndex;

    DeleteCommand(int taskIndex) {
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output;

        try {
            String s = taskList.deleteTask(taskIndex);
            boolean hasOnlyOneTask = taskList.hasOnlyOneTask();
            int size = taskList.size();
            output = OUTPUT_MESSAGE
                    + s
                    + "\n"
                    + TAB
                    + "There "
                    + (hasOnlyOneTask ? "is " : "are ")
                    + size
                    + (hasOnlyOneTask ? " task" : " tasks")
                    + " in the list";
        } catch (DukeIndexOutOfBoundException exception) {
            output = ERROR_MESSAGE;
        }

        ui.printOutput(output);

        super.saveFile(ui, taskList, storage);
    }
}
