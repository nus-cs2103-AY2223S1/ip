package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class AddCommand extends Command {
    String input;

    public AddCommand(Storage storage, Ui ui, TaskList taskList, String input) {
        super(storage, ui, taskList);
        this.input = input;
    }

    @Override
    public void execute() throws DukeException {
        taskList.addTask(Task.createTask(input));
    }
}
