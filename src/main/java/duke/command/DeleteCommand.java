package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.Ui;
public class DeleteCommand extends Command{
    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("\tYou currently have no tasks in your list to delete.");
        } else {
            try {
                Task deletedTask = tasks.deleteTask(index);
                int size = tasks.getSize();
                ui.showDelete(deletedTask, size);
                storage.save(tasks.saveToStorage());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\tTask number does not exist!");
            }
        }
    }
}
