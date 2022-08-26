package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

import java.util.ArrayList;

public class ListCommand extends Command {
    private final ArrayList<String> words;

    public ListCommand(ArrayList<String> words) {
        this.words = words;
    }

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
