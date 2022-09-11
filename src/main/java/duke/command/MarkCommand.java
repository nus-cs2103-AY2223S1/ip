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
    private static final String MARK_TASK = "Great Job on completing this task! ^.^ :\n";
    private static final String UNMARK_TASK = "Grrr, remember to finish your task! =3=:\n";
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
     * @param storage        the storage object
     * @param tasklist       the task list object
     * @param ui             the user interface object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    @Override
    public String execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        StringBuilder output = new StringBuilder();
        int taskNum = Integer.parseInt(words.get(0));
        switch (firstWord) {
        case "mark":
            // Work on implementing error for empty mark argument
            if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
                tasklist.tasks.get(taskNum - 1).markDone();
                output.append(MARK_TASK)
                        .append(tasklist.tasks.get(taskNum - 1));
            } else if (tasklist.tasks.size() == 0) {
                throw new DukeException("There's nothing in your list to mark! T^T");
            } else {
                throw new DukeException("Please enter a valid task number to mark. T^T");
            }
            break;
        case "unmark":
            // Work on implementing error for empty unmark argument
            if (taskNum > 0 && taskNum <= tasklist.tasks.size()) {
                tasklist.tasks.get(taskNum - 1).markUndone();
                output.append(UNMARK_TASK)
                        .append(tasklist.tasks.get(taskNum - 1));
            } else if (tasklist.tasks.size() == 0) {
                throw new DukeException("There's nothing in your list to unmark! T^T");
            } else {
                throw new DukeException("Please enter a valid task number to unmark. T^T");
            }
            break;
        default:
            // Defensive coding for default statement.
            output.append(Messages.UNKNOWN_COMMAND);
        }
        return output.toString();
    }
}
