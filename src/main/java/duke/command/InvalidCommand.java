package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class InvalidCommand extends Command {
    private final String input;

    public InvalidCommand(String input) {
        this.input = input;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        System.out.println(ui.SPACER + "\n"
                + "Sorry, I don't understand. T^T\n"
                + "Please start your command with list, mark, unmark, todo, deadline, event or bye. :')\n"
                + ui.SPACER);
    }
}
