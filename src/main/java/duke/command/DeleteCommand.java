package duke.command;

import java.util.ArrayList;

import duke.common.Messages;
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
     * @param storage the storage object
     * @param tasklist the task list object
     * @param ui the user interface object
     * @throws DukeException if the user input is unrecognised
     */
    public void execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        String input = String.join(" ", words);
        // Work on implementing error for "delete hello"
        int taskNum = Integer.parseInt(input);
        if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
            String deletedTask = tasklist.tasks.get(taskNum - 1).toString();
            tasklist.deleteTask(taskNum);
            System.out.println(Messages.SPACER + "\n"
                    + "Boo~ Don't be a quitter! I've removed this task for you. =3=\n"
                    + deletedTask + "\n"
                    + "You have " + tasklist.tasks.size()
                    + (tasklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                    + Messages.SPACER);
        } else if (tasklist.tasks.size() == 0) {
            throw new DukeException(Messages.SPACER + "\n"
                    + "There's nothing in your list to delete! T^T\n"
                    + Messages.SPACER);
        } else {
            throw new DukeException(Messages.SPACER + "\n"
                    + "Please enter a valid task number to delete. T^T\n"
                    + Messages.SPACER);
        }
    }
}
