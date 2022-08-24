package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private ToDo td;

    public TodoCommand(ToDo td) {
        this.td = td;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(td);
    }

}
