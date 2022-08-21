package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        ui.printActiveTasks(list);
    }
}
