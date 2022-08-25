package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

public class MarkCommand extends Command {
    int indexToMark;

    public MarkCommand(int indexToMark) {
        super();
        this.indexToMark = indexToMark;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (indexToMark > tasks.getSize() || indexToMark < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        ui.printMark(tasks.markTask(this.indexToMark));
        storage.save(tasks.getTaskList());
    }
}
