package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    public MarkCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(description.substring(5)) - 1;
            tasks.markTaskAsDone(index);
            ui.printTaskMarkedDone(tasks.get(index), tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You cannot mark a non-existent task as done.");
        }
    }
}
