package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

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
     * @param storage the storage object
     * @param tasklist the task list object
     * @param ui the user interface object
     * @throws DukeException if the user input is unrecognised
     */
    public void execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        if (words.size() == 0) {
            System.out.println(ui.SPACER + "\n"
                    + "Here's your list ^3^:\n"
                    + tasklist.printList() + "\n"
                    + ui.SPACER);
        } else {
            throw new DukeException(ui.SPACER + "\n"
                    + "Please only enter 'list' to view your list. T^T\n"
                    + ui.SPACER);
        }
    }
}
