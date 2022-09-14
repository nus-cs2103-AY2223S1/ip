package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String instruction) {
        super(instruction);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(instruction.substring(7)) - 1;
            Task deleted = tasks.get(index);
            tasks.delete(index);
            ui.printDeleteMessage(deleted);
            storage.saveList(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.printError(new DukeException(e.getMessage()));
        }
    }

}
