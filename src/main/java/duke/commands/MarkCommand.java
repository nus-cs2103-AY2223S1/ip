package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeIndexOutOfBoundsException;

public class MarkCommand extends Command {

    public MarkCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(description.substring(5)) - 1;
            tasks.markTaskAsDone(index);
            ui.printTaskMarkedDone(tasks.get(index), tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException("OOPS!!! You cannot mark a non-existent task as done.");
        }
    }
}
