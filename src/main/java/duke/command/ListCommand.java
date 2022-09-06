package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList list) throws DukeException {
        Ui.printList(list);
    }
}
