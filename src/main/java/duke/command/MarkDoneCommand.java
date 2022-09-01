package duke.command;

import duke.Duke;
import duke.exception.DukeIndexOutOfBoundException;
import duke.util.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class that marks a task as done, displays the output, and saves the list.
 */
public class MarkDoneCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Good to hear that! I have marked this as done: \n" + Duke.TAB;
    private static final String ERROR_MESSAGE =
            "Oops! Do check the index range, and the format should be \"mark <index>\"";

    private final int taskIndex;

    /**
     * The standard constructor.
     */
    public MarkDoneCommand(int taskIndex) {
        super(CommandType.MARK_DONE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command concretely.
     * Marks a task as done, displays the output, and saves the list.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        String output;

        try {
            output = OUTPUT_MESSAGE + taskList.markTaskDone(taskIndex);
        } catch (DukeIndexOutOfBoundException exception) {
            output = ERROR_MESSAGE;
        }

        cliUi.printOutput(output);

        super.saveFile(cliUi, taskList, storage);
    }
}
