package duke.commands;

import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

public class ExitCommand extends Command {
    public ExitCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    public String sayGoodbye() {
        return ui.sayGoodbye();
    }
}
