package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command to output the users' list; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class ListCommand extends Command {
    private final ArrayList<String> words;

    /**
     * Constructor for ListCommand.
     *
     * @param words the remaining user input after the first keyword
     */
    public ListCommand(ArrayList<String> words) {
        this.words = words;
    }

    /**
     * Executes the command for "list" keyword.
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
        StringBuilder output = new StringBuilder();
        if (words.size() == 0) {
            output.append("Here's your list ^3^:\n")
                    .append(tasklist.printList());
        } else {
            throw new DukeException("Please only enter 'list' to view your list. T^T");
        }
        return output.toString();
    }
}
