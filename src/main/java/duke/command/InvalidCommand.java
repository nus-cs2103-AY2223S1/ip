package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

import java.util.ArrayList;

public class InvalidCommand extends Command {

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        System.out.println(ui.SPACER + "\n"
                + "Sorry, I don't understand. T^T\n"
                + "Please start your command with list, mark, unmark, todo, deadline, event or bye. :')\n"
                + ui.SPACER);
    }
}
