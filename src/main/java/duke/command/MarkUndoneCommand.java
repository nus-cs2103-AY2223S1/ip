package duke.command;

import duke.Duke;
import duke.exception.DukeIndexOutOfBoundException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A command class that marks a task as undone, displays the output, and saves the list.
 */
public class MarkUndoneCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Sure, I have marked this as not done yet.\n" + Duke.TAB;
    private static final String ERROR_MESSAGE =
            "Oops! Do check the index range, and the format should be \"unmark <index>\"";

    private final int taskIndex;

    /**
     * The standard constructor.
     */
    public MarkUndoneCommand(int taskIndex) {
        super(CommandType.MARK_UNDONE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command concretely.
     * Marks a task as undone, displays the output, and saves the list.
     *
     * @param ui An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
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
