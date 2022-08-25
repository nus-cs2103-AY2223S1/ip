package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;

public class DeleteCommand extends Command {
    int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        super();
        this.deleteIndex = deleteIndex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (deleteIndex > tasks.getSize() || deleteIndex < 0) {
            throw new InvalidInputException("The index provided is not within the list.");
        }
        String task = tasks.deleteTask(this.deleteIndex);
        ui.printDelete(task, tasks.getSize());
        storage.save(tasks.getTaskList());
    }
}
