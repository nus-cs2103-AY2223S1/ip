package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private final String[] str;

    public DeleteCommand(String[] str) {
        this.str = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(str[1]) - 1;
        } catch (Exception e) {
            ui.emptyDescription();
        }

        if (index > Task.lsSize() || index < 0) {
            ui.invalidTask();
        }
        tasks.get(index).remove();
        tasks.remove(index);
        storage.writeFile(tasks);
    }
}
