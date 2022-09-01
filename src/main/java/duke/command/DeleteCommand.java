package duke.command;

import static duke.Duke.TAB;

import duke.exception.DukeIndexOutOfBoundException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.CliUi;

/**
 * A command class that deletes a task, displays the output, and saves the list.
 */
public class DeleteCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Sure, I have removed this task from the list: \n" + TAB;
    private static final String ERROR_MESSAGE =
            "Oops! Do check the index range, and the format should be \"delete <index>\"";

    private final int taskIndex;

    /**
     * The standard constructor.
     */
    public DeleteCommand(int taskIndex) {
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command concretely.
     * Deletes a task, displays the output, and saves the list.
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
                    + " in the list.";
        } catch (DukeIndexOutOfBoundException exception) {
            output = ERROR_MESSAGE;
        }

        cliUi.printOutput(output);

        super.saveFile(cliUi, taskList, storage);
    }
}
