package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

import java.util.ArrayList;
import java.util.Arrays;

public class ListCommand extends Command {
    private final String input;

    public ListCommand(String input) {
        this.input = input;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));
        if (words.size() == 0) {
            System.out.println(ui.SPACER + "\n"
                    + "Here's your list ^3^:\n"
                    + tasks.printList() + "\n"
                    + ui.SPACER);
        } else {
            throw new DukeException(ui.SPACER + "\n"
                    + "Please only enter 'list' to view your list. T^T\n"
                    + ui.SPACER);
        }
    }
}
