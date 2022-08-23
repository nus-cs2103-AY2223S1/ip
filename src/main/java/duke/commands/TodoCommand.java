package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private ToDo td;

    public static final String COMMAND_WORD = "todo";

    public TodoCommand(ToDo td) {
        this.td = td;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(td);
    }

}
