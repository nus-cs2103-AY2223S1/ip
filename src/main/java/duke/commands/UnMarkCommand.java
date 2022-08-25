package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnMarkCommand extends Command {

    public UnMarkCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(description.substring(7)) - 1;
            tasks.markTaskAsUndone(index);
            ui.printTaskMarkedUndone(tasks.get(index), tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You cannot mark a non-existent task as undone.");
        }
    }
}
