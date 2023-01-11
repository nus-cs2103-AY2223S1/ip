package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * DeleteCommand deletes task from current task list based on user input.
 */
public class DeleteCommand extends Command {

    /**
     * Command line input for delete.
     */
    private String input;
    private String intRegex = "[0-9]+";

    /**
     * Creates a new DeleteCommand.
     *
     * @param input User input read from command line interface.
     */
    public DeleteCommand(String input) {
        this.input = input.strip();
    }

    /**
     * Deletes the task from the task list.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by duke.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!input.matches(intRegex)) {
            throw new DukeException("Please input an integer index from the task list! \n" +
                    "Follow the format delete (integer)\n");
        }
        int i = Integer.parseInt(input);
        if (i < 0 || i > tasks.getCount()) {
            throw new DukeException("Please input an index that contains a task !\n" +
                    "If you are not sure what index to use, you can list the tasks available " +
                    "by inputting 'list' to me!\n");
        }
        Task removed = tasks.remove(i - 1);
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("Noted. I have removed this task:\n");
        toReturn.append(removed + "\n");
        toReturn.append("Now you have " + tasks.getCount() + " tasks in the list.\n");
        return toReturn.toString();
    }
}
