package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command to delete a task from the tasklist; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class DeleteCommand extends Command {
    private static final String REMOVED_TASK = "Boo~ Don't be a quitter! I've removed this task for you. =3=\n";
    private final ArrayList<String> words;

    /**
     * Constructor for DeleteCommand.
     *
     * @param words the remaining user input after the first keyword
     */
    public DeleteCommand(ArrayList<String> words) {
        this.words = words;
    }

    /**
     * Executes the command for "delete" keyword.
     * This is the main way for outputting bot replies.
     *
     * @param storage        the storage object
     * @param tasklist       the task list object
     * @param ui             the user interface object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    @Override
    public String execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        String input = String.join(" ", words);
        StringBuilder output = new StringBuilder();
        // Work on implementing error for "delete hello"
        int taskNum = Integer.parseInt(input);
        if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
            String deletedTask = tasklist.tasks.get(taskNum - 1).toString();
            tasklist.deleteTask(taskNum);
            output.append(REMOVED_TASK)
                    .append(deletedTask).append("\n")
                    .append("You have ").append(tasklist.tasks.size())
                    .append((tasklist.tasks.size() == 1 ? " task! :D" : " tasks! :D"));
        } else if (tasklist.tasks.size() == 0) {
            throw new DukeException("There's nothing in your list to delete! T^T");
        } else {
            throw new DukeException("Please enter a valid task number to delete. T^T");
        }
        return output.toString();
    }
}
