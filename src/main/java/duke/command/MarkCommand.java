package duke.command;

import java.util.ArrayList;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command to mark or unmark a task; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class MarkCommand extends Command {
    private final ArrayList<String> words;
    private final String firstWord;

    /**
     * Constructor for MarkCommand.
     *
     * @param words the remaining user input after the first keyword
     * @param firstWord the first word in the user input
     */
    public MarkCommand(ArrayList<String> words, String firstWord) {
        this.words = words;
        this.firstWord = firstWord;
    }

    /**
     * Executes command for "mark" and "unmark" keywords.
     * This is the main way for outputting bot replies
     *
     * @param storage the storage object
     * @param tasklist the task list object
     * @param ui the user interface object
     * @throws DukeException if the user input is unrecognised
     */
    public void execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        switch (firstWord) {
        case "mark":
            // Work on implementing error for empty mark argument
            int taskNum = Integer.parseInt(words.get(0));
            if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
                tasklist.tasks.get(taskNum - 1).markDone();
                System.out.println(Messages.SPACER + "\n"
                        + "Great Job on completing this task! ^.^ :\n"
                        + tasklist.printTaskStatus(taskNum - 1) + "\n"
                        + Messages.SPACER);
            } else if (tasklist.tasks.size() == 0) {
                throw new DukeException(Messages.SPACER + "\n"
                        + "There's nothing in your list to mark! T^T\n"
                        + Messages.SPACER);
            } else {
                throw new DukeException(Messages.SPACER + "\n"
                        + "Please enter a valid task number to mark. T^T\n"
                        + Messages.SPACER);
            }
            break;
        case "unmark":
            // Work on implementing error for empty unmark argument
            taskNum = Integer.parseInt(words.get(0));
            if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
                tasklist.tasks.get(taskNum - 1).markUndone();
                System.out.println(Messages.SPACER + "\n"
                        + "Grrr, remember to finish your task! =3=:\n"
                        + tasklist.printTaskStatus(taskNum - 1) + "\n"
                        + Messages.SPACER);
            } else if (tasklist.tasks.size() == 0) {
                throw new DukeException(Messages.SPACER + "\n"
                        + "There's nothing in your list to unmark! T^T\n"
                        + Messages.SPACER);
            } else {
                throw new DukeException(Messages.SPACER + "\n"
                        + "Please enter a valid task number to unmark. T^T\n"
                        + Messages.SPACER);
            }
            break;
        default:
            // Defensive coding for default statement.
            System.out.println(Messages.SPACER + "\n"
                    + Messages.UNKNOWN_COMMAND
                    + Messages.SPACER);
        }
    }
}
