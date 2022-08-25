package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

public class UnmarkCommand extends Command {
    int indexToUnmark;

    public UnmarkCommand(int indexToUnmark) {
        super();
        this.indexToUnmark = indexToUnmark;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (indexToUnmark > tasks.getSize() || indexToUnmark < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        ui.printUnmark(tasks.unMarkTask(this.indexToUnmark));
        storage.save(tasks.getTaskList());
    }
}
